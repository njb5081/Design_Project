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

    private CashAccount c1 = new CashAccount(100, "test1");
    private CashAccount c2 = new CashAccount(50, "test2");

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Transactions");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 400);

        final HashMap<String, CashAccount> cashAccounts =  new HashMap<String, CashAccount>();

        cashAccounts.put(c1.toString(), c1);
        cashAccounts.put(c2.toString(), c2);

        ObservableList<String> options = FXCollections.observableArrayList();

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
        final Label toAccountBalanceLabel = new Label("     Account Balance: None Selected");
        final Label fromAccountNameLabel = new Label("     Account Name: None Selected");
        final Label fromAccountBalanceLabel = new Label("     Account Balance: None Selected");

        Button transFunds = new Button("Transfer");

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

                        Transfer cashTransfer = new Transfer(tempAmount, tempToAccount, tempFromAccount);

                        cashTransfer.execute();

                        toAccountNameLabel.setText("     Account Name: " + tempToAccount.toString());
                        toAccountBalanceLabel.setText("     Account Balance: " + Integer.toString(tempToAccount.getBalance()));
                        fromAccountNameLabel.setText("     Account Name: " + tempFromAccount.toString());
                        fromAccountBalanceLabel.setText("     Account Balance: " + Integer.toString(tempFromAccount.getBalance()));


                    } else{
                        transFundsLabel.setText("Invalid Input");
                    }

                } else {
                    transFundsLabel.setText("Invalid Input");
                }
            }
        });

        toAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                toAccountNameLabel.setText("     Account Name: " + toAccount.getValue().toString());
                toAccountBalanceLabel.setText("     Account Balance: " + Integer.toString(cashAccounts.get(toAccount.getValue()).getBalance()));
            }
        });

        fromAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                fromAccountNameLabel.setText("     Account Name: " + fromAccount.getValue().toString());
                fromAccountBalanceLabel.setText("     Account Balance: " + Integer.toString(cashAccounts.get(fromAccount.getValue()).getBalance()));
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

        grid.add(box1, 1 , 10);
        grid.add(box2, 1 , 20);
        grid.add(box3, 1 , 40);
        grid.add(box4, 1 , 60);
        grid.add(box5, 1 , 120);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
        
    }

}
