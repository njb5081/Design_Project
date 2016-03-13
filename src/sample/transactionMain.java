package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashMap;


public class transactionMain extends Application{

    private Logger log = new Logger();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Transactions");

        final GridPane transactionGrid = new GridPane();
        transactionGrid.setAlignment(Pos.TOP_LEFT);
        transactionGrid.setHgap(1);
        transactionGrid.setVgap(1);
        transactionGrid.setPadding(new Insets(25, 25, 25, 25));

        final GridPane createCashAccountGrid = new GridPane();
        createCashAccountGrid.setAlignment(Pos.TOP_LEFT);
        createCashAccountGrid.setHgap(1);
        createCashAccountGrid.setVgap(1);
        createCashAccountGrid.setPadding(new Insets(25, 25, 25, 25));

        final Scene scene = new Scene(transactionGrid, 900, 450);

        final HashMap<String, CashAccount> cashAccounts =  new HashMap<String, CashAccount>();
        final HashMap<String, Equity> equities =  new HashMap<String, Equity>();

        final ObservableList<String> optionsCashAccounts = FXCollections.observableArrayList();
        final ObservableList<String> optionsEquities = FXCollections.observableArrayList();

        optionsCashAccounts.addAll(cashAccounts.keySet());
        optionsEquities.addAll(equities.keySet());

        final ComboBox fromAccount = new ComboBox(optionsCashAccounts);
        final ComboBox toAccount = new ComboBox(optionsCashAccounts);
        final TextField transAmount = new TextField(){

            @Override public void replaceText(int start, int end, String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceText(start, end, text);
                }
            }

            @Override public void replaceSelection(String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceSelection(text);
                }
            }
        };

        final Label transFundsLabel = new Label("Choose Cash Accounts");
        final Label toAccountLabel = new Label("Add funds to: ");
        final Label fromAccountLabel = new Label("Take funds from: ");
        final Label amountLabel = new Label("Amount to transfer: ");

        final Label toAccountNameLabel = new Label("     Account Name: None Selected");
        final Label toAccountBalanceLabel = new Label("     Account Balance: $0");
        final Label fromAccountNameLabel = new Label("     Account Name: None Selected");
        final Label fromAccountBalanceLabel = new Label("     Account Balance: $0");

        final Button transFunds = new Button("Transfer");
        final Button newCashAccount = new Button("Add Cash Account");

        final Label newCashAccountLabel = new Label("Enter New Cash Account Details");
        final Label newCashAccountNameLabel = new Label("Name of New Cash Account: ");
        final Label newCashAccountBalanceLabel = new Label("Balance of New Cash Account: ");

        final TextField newCashAccountName = new TextField();
        final TextField newCashAccountBalance = new TextField(){

            @Override
            public void replaceText(int start, int end, String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceSelection(text);
                }
            }
        };

        final ComboBox sellCashAccount = new ComboBox(optionsCashAccounts);
        final ComboBox sellEquity = new ComboBox(optionsEquities);

        final Label sellTransactionLabel = new Label("Choose Equity to Sell");
        final Label sellEquityNameLabel = new Label("     Equity Name: None Selected");
        final Label sellEquityValueLabel = new Label("     Equity Value: None Selected");
        final Label sellEquityOwnedLabel = new Label("     Amount Owned: None Selected");
        final Label sellCashAccountNameLabel = new Label("     Account Name: None Selected");
        final Label sellCashAccountBalanceLabel = new Label("     Account Balance: None Selected");
        final Label sellCashAccountLabel = new Label("Add funds to:");
        final Label sellEquityLabel = new Label("Sell this equity:");
        final Label sellAmountLabel = new Label("Amount of selected equity to sell:");

        final Button sellEquityButton = new Button("Sell");

        final TextField sellEquityAmount = new TextField(){

            @Override
            public void replaceText(int start, int end, String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceSelection(text);
                }
            }
        };

        final ComboBox buyCashAccount = new ComboBox(optionsCashAccounts);
        final ComboBox buyEquity = new ComboBox(optionsEquities);

        final Label buyTransactionLabel = new Label("Choose Equity to Buy");
        final Label buyEquityNameLabel = new Label("     Equity Name: None Selected");
        final Label buyEquityValueLabel = new Label("     Equity Value: None Selected");
        final Label buyEquityOwnedLabel = new Label("     Amount Owned: None Selected");
        final Label buyCashAccountNameLabel = new Label("     Account Name: None Selected");
        final Label buyCashAccountBalanceLabel = new Label("     Account Balance: None Selected");
        final Label buyCashAccountLabel = new Label("Deduct funds from:");
        final Label buyEquityLabel = new Label("Buy this equity:");
        final Label buyAmountLabel = new Label("Amount of selected equity to buy:");

        final Button buyEquityButton = new Button("Buy");

        final TextField buyEquityAmount = new TextField(){

            @Override
            public void replaceText(int start, int end, String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text) {
                if (text.matches("[0-9]*")) {
                    super.replaceSelection(text);
                }
            }
        };

        final Button createCashAccount = new Button("Create Cash Account");
        final Button returnTrans = new Button("Return");

        sellEquityButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (sellCashAccount.getValue() != null &
                        sellEquity.getValue() != null &
                        !sellEquityAmount.getText().equals("")
                        ) {

                    CashAccount tempSellAccount = cashAccounts.get(toAccount.getValue());
                    Equity tempSellEquity = equities.get(sellEquity.getValue());
                    int tempAmount = Integer.parseInt(transAmount.getText());

                    if(tempSellAccount.getBalance() >= tempSellEquity.getSharePrice() * tempAmount) {

                        SellEquity equitySale = new SellEquity(tempAmount, tempSellAccount, tempSellEquity, log);
                        equitySale.execute();

                        sellTransactionLabel.setText("Sale Successful");

                    } else{
                        sellTransactionLabel.setText("Invalid Input");
                    }

                } else {
                    sellTransactionLabel.setText("Invalid Input");
                }
            }
        });

        buyEquityButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (buyCashAccount.getValue() != null &
                        buyEquity.getValue() != null &
                        !buyEquityAmount.getText().equals("")
                        ) {

                    CashAccount tempBuyAccount = cashAccounts.get(toAccount.getValue());
                    Equity tempBuyEquity = equities.get(buyEquity.getValue());
                    int tempAmount = Integer.parseInt(transAmount.getText());

                    if(tempBuyAccount.getBalance() >= tempBuyEquity.getSharePrice() * tempAmount) {

                        BuyEquity equitySale = new BuyEquity(tempAmount, tempBuyAccount, tempBuyEquity, log);
                        equitySale.execute();

                        buyTransactionLabel.setText("Purchase Successful");

                    } else{
                        buyTransactionLabel.setText("Invalid Input");
                    }

                } else {
                    buyTransactionLabel.setText("Invalid Input");
                }
            }
        });

        transFunds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (fromAccount.getValue() != null &
                        toAccount.getValue() != null &
                        toAccount.getValue() != fromAccount.getValue() &
                        !transAmount.getText().equals("")
                        ) {

                    CashAccount tempToAccount = cashAccounts.get(toAccount.getValue());
                    CashAccount tempFromAccount = cashAccounts.get(fromAccount.getValue());
                    int tempAmount = Integer.parseInt(transAmount.getText());

                    if(tempFromAccount.getBalance() >= tempAmount) {

                        Transfer cashTransfer = new Transfer(tempAmount, tempToAccount, tempFromAccount, log);

                        cashTransfer.execute();

                        toAccountNameLabel.setText("     Account Name: " + tempToAccount.toString());
                        toAccountBalanceLabel.setText("     Account Balance: $" + Double.toString(tempToAccount.getBalance()));
                        fromAccountNameLabel.setText("     Account Name: " + tempFromAccount.toString());
                        fromAccountBalanceLabel.setText("     Account Balance: $" + Double.toString(tempFromAccount.getBalance()));

                        transFundsLabel.setText("Transfer Successful");

                    } else{
                        transFundsLabel.setText("Invalid Input");
                    }

                } else {
                    transFundsLabel.setText("Invalid Input");
                }
            }
        });

        newCashAccount.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene.setRoot(createCashAccountGrid);
            }
        });

        returnTrans.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene.setRoot(transactionGrid);
            }
        });

        createCashAccount.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if(!newCashAccountBalance.getText().equals("") &
                        !newCashAccountName.getText().equals("") &
                        !cashAccounts.keySet().contains(newCashAccountName.getText())){

                    CashAccount tempNewCashAccount = new CashAccount(Integer.parseInt(newCashAccountBalance.getText()), newCashAccountName.getText());

                    cashAccounts.put(tempNewCashAccount.toString(), tempNewCashAccount);

                    optionsCashAccounts.add(tempNewCashAccount.toString());
                    toAccount.setItems(optionsCashAccounts);
                    fromAccount.setItems(optionsCashAccounts);

                    newCashAccountLabel.setText("Account Created Successfully");

                }else {
                    newCashAccountLabel.setText("Invalid Input");
                }

            }
        });

        toAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                toAccountNameLabel.setText("     Account Name: " + toAccount.getValue().toString());
                toAccountBalanceLabel.setText("     Account Balance: $" + Double.toString(cashAccounts.get(toAccount.getValue()).getBalance()));
            }
        });

        fromAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                fromAccountNameLabel.setText("     Account Name: " + fromAccount.getValue().toString());
                fromAccountBalanceLabel.setText("     Account Balance: $" + Double.toString(cashAccounts.get(fromAccount.getValue()).getBalance()));
            }
        });

        HBox box1Trans = new HBox();
        VBox box2Trans = new VBox();
        VBox box3Trans = new VBox();
        VBox box4Trans = new VBox();
        HBox box5Trans = new HBox();

        box1Trans.setAlignment(Pos.TOP_LEFT);
        box2Trans.setAlignment(Pos.TOP_LEFT);
        box3Trans.setAlignment(Pos.TOP_LEFT);
        box4Trans.setAlignment(Pos.TOP_LEFT);
        box5Trans.setAlignment(Pos.TOP_LEFT);

        box1Trans.getChildren().add(transFundsLabel);

        box2Trans.getChildren().add(fromAccountLabel);
        box2Trans.getChildren().add(fromAccount);
        box2Trans.getChildren().add(fromAccountNameLabel);
        box2Trans.getChildren().add(fromAccountBalanceLabel);

        box3Trans.getChildren().add(toAccountLabel);
        box3Trans.getChildren().add(toAccount);
        box3Trans.getChildren().add(toAccountNameLabel);
        box3Trans.getChildren().add(toAccountBalanceLabel);

        box4Trans.getChildren().add(amountLabel);
        box4Trans.getChildren().add(transAmount);

        box5Trans.getChildren().add(transFunds);
        box5Trans.getChildren().add(newCashAccount);

        transactionGrid.add(box1Trans, 1 , 10);
        transactionGrid.add(box2Trans, 1 , 20);
        transactionGrid.add(box3Trans, 1 , 40);
        transactionGrid.add(box4Trans, 1 , 60);
        transactionGrid.add(box5Trans, 1 , 120);

        HBox box1Buy = new HBox();
        VBox box2Buy = new VBox();
        VBox box3Buy = new VBox();
        VBox box4Buy = new VBox();
        HBox box5Buy = new HBox();

        box1Buy.getChildren().add(buyTransactionLabel);

        box2Buy.getChildren().add(buyEquityLabel);
        box2Buy.getChildren().add(buyEquity);
        box2Buy.getChildren().add(buyEquityNameLabel);
        box2Buy.getChildren().add(buyEquityValueLabel);
        box2Buy.getChildren().add(buyEquityOwnedLabel);

        box3Buy.getChildren().add(buyCashAccountLabel);
        box3Buy.getChildren().add(buyCashAccount);
        box3Buy.getChildren().add(buyCashAccountNameLabel);
        box3Buy.getChildren().add(buyCashAccountBalanceLabel);

        box4Buy.getChildren().add(buyAmountLabel);
        box4Buy.getChildren().add(buyEquityAmount);

        box5Buy.getChildren().add(buyEquityButton);

        transactionGrid.add(box1Buy, 200 , 10);
        transactionGrid.add(box2Buy, 200 , 20);
        transactionGrid.add(box3Buy, 200 , 40);
        transactionGrid.add(box4Buy, 200 , 60);
        transactionGrid.add(box5Buy, 200 , 120);

        HBox box1Sell = new HBox();
        VBox box2Sell = new VBox();
        VBox box3Sell = new VBox();
        VBox box4Sell = new VBox();
        HBox box5Sell = new HBox();

        box1Sell.getChildren().add(sellTransactionLabel);

        box2Sell.getChildren().add(sellEquityLabel);
        box2Sell.getChildren().add(sellEquity);
        box2Sell.getChildren().add(sellEquityNameLabel);
        box2Sell.getChildren().add(sellEquityValueLabel);
        box2Sell.getChildren().add(sellEquityOwnedLabel);

        box3Sell.getChildren().add(sellCashAccountLabel);
        box3Sell.getChildren().add(sellCashAccount);
        box3Sell.getChildren().add(sellCashAccountNameLabel);
        box3Sell.getChildren().add(sellCashAccountBalanceLabel);

        box4Sell.getChildren().add(sellAmountLabel);
        box4Sell.getChildren().add(sellEquityAmount);

        box5Sell.getChildren().add(sellEquityButton);

        transactionGrid.add(box1Sell, 100 , 10);
        transactionGrid.add(box2Sell, 100 , 20);
        transactionGrid.add(box3Sell, 100 , 40);
        transactionGrid.add(box4Sell, 100 , 60);
        transactionGrid.add(box5Sell, 100 , 120);

        HBox box1CA = new HBox();
        VBox box2CA = new VBox();
        VBox box3CA = new VBox();
        HBox box4CA = new HBox();

        box1CA.getChildren().add(newCashAccountLabel);

        box2CA.getChildren().add(newCashAccountNameLabel);
        box2CA.getChildren().add(newCashAccountName);

        box3CA.getChildren().add(newCashAccountBalanceLabel);
        box3CA.getChildren().add(newCashAccountBalance);

        box4CA.getChildren().add(createCashAccount);
        box4CA.getChildren().add(returnTrans);

        createCashAccountGrid.add(box1CA, 1 ,10);
        createCashAccountGrid.add(box2CA, 1, 20);
        createCashAccountGrid.add(box3CA, 1, 40);
        createCashAccountGrid.add(box4CA, 1, 120);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
        
    }

}
