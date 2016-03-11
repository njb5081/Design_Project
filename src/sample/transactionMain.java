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

        final GridPane transferCashGrid = new GridPane();
        transferCashGrid.setAlignment(Pos.TOP_LEFT);
        transferCashGrid.setHgap(1);
        transferCashGrid.setVgap(1);
        transferCashGrid.setPadding(new Insets(25, 25, 25, 25));


        final GridPane createCashAccountGrid = new GridPane();
        createCashAccountGrid.setAlignment(Pos.TOP_LEFT);
        createCashAccountGrid.setHgap(1);
        createCashAccountGrid.setVgap(1);
        createCashAccountGrid.setPadding(new Insets(25, 25, 25, 25));

        final Scene scene = new Scene(transferCashGrid, 300, 400);

        final HashMap<String, CashAccount> cashAccounts =  new HashMap<String, CashAccount>();

        final ObservableList<String> options = FXCollections.observableArrayList();

        options.addAll(cashAccounts.keySet());

        final ComboBox fromAccount = new ComboBox(options);
        final ComboBox toAccount = new ComboBox(options);
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


        final Button createCashAccount = new Button("Create Cash Account");
        final Button returnTrans = new Button("Return To Transactions");

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
                        toAccountBalanceLabel.setText("     Account Balance: $" + Integer.toString(tempToAccount.getBalance()));
                        fromAccountNameLabel.setText("     Account Name: " + tempFromAccount.toString());
                        fromAccountBalanceLabel.setText("     Account Balance: $" + Integer.toString(tempFromAccount.getBalance()));


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
                scene.setRoot(transferCashGrid);
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

                    options.add(tempNewCashAccount.toString());
                    toAccount.setItems(options);
                    fromAccount.setItems(options);

                }else {
                    newCashAccountLabel.setText("Invalid Input");
                }

            }
        });

        toAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                toAccountNameLabel.setText("     Account Name: " + toAccount.getValue().toString());
                toAccountBalanceLabel.setText("     Account Balance: $" + Integer.toString(cashAccounts.get(toAccount.getValue()).getBalance()));
            }
        });

        fromAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                fromAccountNameLabel.setText("     Account Name: " + fromAccount.getValue().toString());
                fromAccountBalanceLabel.setText("     Account Balance: $" + Integer.toString(cashAccounts.get(fromAccount.getValue()).getBalance()));
            }
        });

        HBox box1 = new HBox();
        VBox box2 = new VBox();
        VBox box3 = new VBox();
        VBox box4 = new VBox();
        HBox box5 = new HBox();

        box1.setAlignment(Pos.TOP_LEFT);
        box2.setAlignment(Pos.TOP_LEFT);
        box3.setAlignment(Pos.TOP_LEFT);
        box4.setAlignment(Pos.TOP_LEFT);
        box5.setAlignment(Pos.TOP_LEFT);

        box1.getChildren().add(transFundsLabel);

        box2.getChildren().add(fromAccountLabel);
        box2.getChildren().add(fromAccount);
        box2.getChildren().add(fromAccountNameLabel);
        box2.getChildren().add(fromAccountBalanceLabel);

        box3.getChildren().add(toAccountLabel);
        box3.getChildren().add(toAccount);
        box3.getChildren().add(toAccountNameLabel);
        box3.getChildren().add(toAccountBalanceLabel);

        box4.getChildren().add(amountLabel);
        box4.getChildren().add(transAmount);

        box5.getChildren().add(transFunds);
        box5.getChildren().add(newCashAccount);

        transferCashGrid.add(box1, 1 , 10);
        transferCashGrid.add(box2, 1 , 20);
        transferCashGrid.add(box3, 1 , 40);
        transferCashGrid.add(box4, 1 , 60);
        transferCashGrid.add(box5, 1 , 120);

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
