package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

import javax.sound.sampled.Port;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Main extends Application {

    Stage window;
    Scene scene1, scene2, scene3, scene4, scene5;
    static data userData = new data();
    TextField portValue;
    String user;
    private Logger log = userData.getLog();

    Map<String, List<String>> indexMap = userData.getIndexMap();
    Map<String, Equity> equityMap = userData.getEquityMap();

    @Override
    public void start(Stage primaryStage) throws Exception{
        loginScene(primaryStage);
    }

    /*
    * create login scene
    * */
    public void loginScene(Stage mainStage){
        window = mainStage;
        window.setTitle("Login page");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));

        scene1 = new Scene(grid, 300, 300);

        Text sceneTitle = new Text("welcome");
        sceneTitle.setFont(Font.font("Arial"));
        grid.add(sceneTitle, 0 , 0 ,2 ,1);

        //user input
        final Label username = new Label("username");
        grid.add(username, 0, 1);
        final TextField userField = new TextField();
        grid.add(userField, 1, 1);
        final Label password = new Label("password");
        grid.add(password, 0, 2);
        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        final Text message = new Text();
        grid.add(message, 1, 7);

        //add button login
        Button login = new Button("Sign In");
        HBox box = new HBox(10);
        box.setAlignment(Pos.BOTTOM_RIGHT);

        //action for button
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User loginUser = new User(userField.getText(),pwBox.getText());
                user = userField.getText();
                if(userData.isUserExist(loginUser)){
                    message.setFill(Color.FIREBRICK);
                    message.setText("successful sign in");
                    portfolioScene(window, loginUser.username());
                    //simulationScene(window); //change to portfolio scene
                    //-------------------------------------------------------------------------------------------------

                } else {

                    message.setText("Please enter correct information");

                }
            }
        });
        box.getChildren().add(login);
        grid.add(box, 1 , 4);

        //add register account
        Label noAccount = new Label("need new Account?");
        noAccount.autosize();
        grid.add(noAccount, 0, 5);
        Button register = new Button("Register");

        //action
        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registerScene(window);
            }
        });

        HBox box2 = new HBox(10);
        box2.setAlignment(Pos.BOTTOM_RIGHT);
        box2.getChildren().add(register);
        grid.add(box2, 1, 5);
        window.setScene(scene1);
        window.show();
    }

    /*
    * create register scene
    * */
    public void registerScene(Stage mainStage){
        //scene 2 register
        window = mainStage;
        window.setTitle("Register page");
        //window.setScene(scene2);
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(15);
        grid2.setVgap(15);
        grid2.setPadding(new Insets(25, 25, 25, 25));

        scene2 = new Scene(grid2, 300, 300);



        Text registerSceneTitle = new Text("");
        registerSceneTitle.setFont(Font.font("Arial"));
        grid2.add(registerSceneTitle, 0 , 0 ,2 ,1);

        //user input
        Label username = new Label("username");
        grid2.add(username, 0, 1);
        final TextField userField = new TextField();
        grid2.add(userField, 1, 1);
        final Label password = new Label("password");
        grid2.add(password, 0, 2);
        final PasswordField pwBox = new PasswordField();
        grid2.add(pwBox, 1, 2);
        Label confirm = new Label("confirm password");
        grid2.add(confirm, 0, 3);
        final PasswordField confirmPw = new PasswordField();
        grid2.add(confirmPw, 1, 3);

        final Text message = new Text();
        grid2.add(message, 1, 7);

        //add button login
        Button register = new Button("Sign Up");
        HBox box = new HBox(10);
        box.setAlignment(Pos.BOTTOM_RIGHT);

        //action for button
//        register.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//
//                if (pwBox.getText().equals(confirmPw.getText())){
//
//                    User newAccount = new User(userField.getText(),pwBox.getText());
//                    if(!userData.usernameExist(newAccount.username())) {
//                        userData.saveAccount(newAccount);
//                        message.setText("register success");
//                    } else {
//                        message.setText("Account has been created");
//                    }
//
//                } else {
//
//                    message.setText("please confirm the password");
//
//                }
//            }
//        });
        box.getChildren().add(register);
        grid2.add(box, 1 , 4);

        //add register account
        Label needLogin = new Label("need Login?");
        needLogin.autosize();
        grid2.add(needLogin, 0, 6);
        Button login = new Button("login");
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginScene(window);
            }
        });
        grid2.add(login, 1, 6);
        HBox box2 = new HBox(10);
        box2.setAlignment(Pos.BOTTOM_RIGHT);
        box2.getChildren().add(register);
        grid2.add(box2, 1, 5);
        window.setScene(scene2);
        window.show();



        window.show();

    }

    public void loggerScene(final Stage mainStage){
        window = mainStage;
        window.setTitle("Logger");

        final GridPane logGrid = new GridPane();
        logGrid.setAlignment(Pos.TOP_LEFT);
        logGrid.setHgap(1);
        logGrid.setVgap(1);
        logGrid.setPadding(new Insets(25, 25, 25, 25));

        final Label logDescription = new Label("Choose Entry to View");
        final Label entryDescription = new Label("     Entry Description:");

        //PORTFOLIO STUFF START
        final Button portButton = new Button("Go to Portfolio");

        portButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioScene(mainStage, user);
            }
        });
        //PORTFOLIO STUFF END

        scene5 = new Scene(logGrid, 600, 250);

        final HashMap<String, Entry> entries =  new HashMap<String, Entry>();

        for(int i = 0; i < log.getEntries().size(); i++){
            //entries.put(log.getEntries().get(i).getUser(), log.getEntries().get(i));
            if(log.getEntries().get(i).getUser().equals(user)){
                entries.put(log.getEntries().get(i).getDate(), log.getEntries().get(i));
            }

        }

        final ObservableList<String> optionsLog = FXCollections.observableArrayList();
        optionsLog.addAll(entries.keySet());

        VBox logBox = new VBox();
        HBox portBox = new HBox();

        final ComboBox chooseLog = new ComboBox(optionsLog);

        chooseLog.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                entryDescription.setText("     Entry Description: " + entries.get(chooseLog.getValue()));

            }

        });

        logBox.getChildren().add(logDescription);
        logBox.getChildren().add(chooseLog);
        logBox.getChildren().add(entryDescription);

        portBox.getChildren().add(portButton);

        logGrid.add(logBox,1,25);
        logGrid.add(portBox,1,100);

        window.setScene(scene5);
        window.show();
    }

    public void transactionScene(final Stage mainStage){

        window = mainStage;
        window.setTitle("Transactions");

        List<Portfolio> portList = userData.listOfPortfolio();
        Portfolio myPortfolio = portList.get(0);
        for (Portfolio p : portList) {
            if (p.getUserID().equals(user)){
                myPortfolio = p;
            }
        }

        final Portfolio innerMyPortfolio = myPortfolio;

        final HashMap<String, Asset> availableAssets = new HashMap<String, Asset>();

        for(String equityName : equityMap.keySet()){

            availableAssets.put(equityName, equityMap.get(equityName));

        }

        for(String indexName : indexMap.keySet()){

            ArrayList<Equity> tempEquities = new ArrayList<Equity>();

            for(String equityName : indexMap.get(indexName)){
                tempEquities.add(equityMap.get(equityName));
            }

            Asset tempIndexSector = new IndexSector(indexName, tempEquities);
            availableAssets.put(tempIndexSector.getName(), tempIndexSector);

        }


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

        scene4 = new Scene(transactionGrid, 900, 600);

        final HashMap<String, CashAccount> cashAccounts =  new HashMap<String, CashAccount>();
        final HashMap<String, Equity> equitiesOwned =  new HashMap<String, Equity>();
        final HashMap<String, Equity> equitiesForSale =  new HashMap<String, Equity>();

        for (int i = 0; i < myPortfolio.getCashAccounts().size(); i++){
            cashAccounts.put(myPortfolio.getCashAccounts().get(i).toString(),
                    myPortfolio.getCashAccounts().get(i));
        }

        for (int i = 0; i < myPortfolio.getEquities().size(); i++){
            equitiesOwned.put(myPortfolio.getEquities().get(i).toString(),
                    myPortfolio.getEquities().get(i));
        }

        final ObservableList<String> optionsCashAccounts = FXCollections.observableArrayList();
        final ObservableList<String> optionsEquitiesOwned = FXCollections.observableArrayList();
        final ObservableList<String> optionsEquitiesForSale = FXCollections.observableArrayList();

        optionsCashAccounts.addAll(cashAccounts.keySet());
        optionsEquitiesOwned.addAll(equitiesOwned.keySet());
        optionsEquitiesForSale.addAll(availableAssets.keySet());

        final ComboBox fromAccount = new ComboBox(optionsCashAccounts);
        final ComboBox toAccount = new ComboBox(optionsCashAccounts);
        final NumberTextField transAmount = new NumberTextField();

        final Label transFundsLabel = new Label("Choose Cash Accounts");
        final Label toAccountLabel = new Label("Add funds to: ");
        final Label fromAccountLabel = new Label("Take funds from: ");
        final Label amountLabel = new Label("Amount to transfer: ");

        final Label toAccountNameLabel = new Label("     Account Name: None");
        final Label toAccountBalanceLabel = new Label("     Account Balance: $0");
        final Label fromAccountNameLabel = new Label("     Account Name: None ");
        final Label fromAccountBalanceLabel = new Label("     Account Balance: $0");

        final Button transFunds = new Button("Transfer");

        final ComboBox sellCashAccount = new ComboBox(optionsCashAccounts);
        final ComboBox sellEquity = new ComboBox(optionsEquitiesOwned);

        final Label sellTransactionLabel = new Label("Choose Equity to Sell");
        final Label sellEquityNameLabel = new Label("      Name: None");
        final Label sellEquityValueLabel = new Label("      Value: None");
        final Label sellEquityOwnedLabel = new Label("      Amount Owned: None");
        final Label sellCashAccountNameLabel = new Label("      Name: None");
        final Label sellCashAccountBalanceLabel = new Label("      Balance: None");
        final Label sellCashAccountLabel = new Label("Add funds to:");
        final Label sellEquityLabel = new Label("Sell this equity:");
        final Label sellAmountLabel = new Label("Amount of selected equity to sell:");

        final Button sellEquityButton = new Button("Sell");

        final NumberTextField sellEquityAmount = new NumberTextField();

        final ComboBox buyCashAccount = new ComboBox(optionsCashAccounts);
        final ComboBox buyEquity = new ComboBox(optionsEquitiesForSale);

        final Label buyTransactionLabel = new Label("Choose Equity to Buy");
        final Label buyEquityNameLabel = new Label("      Name: None");
        final Label buyEquityValueLabel = new Label("      Value: None");
        final Label buyEquityOwnedLabel = new Label("      Amount Owned: None");
        final Label buyCashAccountNameLabel = new Label("      Name: None");
        final Label buyCashAccountBalanceLabel = new Label("      Balance: None");
        final Label buyCashAccountLabel = new Label("Deduct funds from:");
        final Label buyEquityLabel = new Label("Buy this equity:");
        final Label buyAmountLabel = new Label("Amount of selected equity to buy:");

        final Label toAccountOpenDateLabel = new Label("     Open Date: None");
        final Label fromAccountOpenDateLabel = new Label("     Open Date: None");
        final Label buyAccountOpenDateLabel = new Label("      Open Date: None");
        final Label sellAccountOpenDateLabel = new Label("      Open Date: None");

        final Button buyEquityButton = new Button("Buy");

        final NumberTextField buyEquityAmount = new NumberTextField();

        final Button returnTrans = new Button("Return");

        sellEquityButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (sellCashAccount.getValue() != null &
                        sellEquity.getValue() != null &
                        !sellEquityAmount.getText().equals("")
                        ) {

                    CashAccount tempSellAccount = cashAccounts.get(toAccount.getValue());
                    Equity tempSellEquity = equitiesOwned.get(sellEquity.getValue());
                    int tempAmount = Integer.parseInt(transAmount.getText());

                    if(tempSellAccount.getBalance() >= tempSellEquity.getSharePrice() * tempAmount) {

                        SellEquity equitySale = new SellEquity(tempAmount, tempSellAccount, tempSellEquity, log, innerMyPortfolio);
                        equitySale.execute();

                        sellTransactionLabel.setText("Sale Successful");

                        sellCashAccountNameLabel.setText("      Name: " + sellCashAccount.getValue().toString());
                        sellCashAccountBalanceLabel.setText("      Balance: $" + Double.toString(cashAccounts.get(sellCashAccount.getValue()).getBalance()));
                        sellAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(sellCashAccount.getValue()).getOpenDate());
                        userData.updateLogger(log);

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
                //Check to make sure a cash account and equity is selected and an amount is specified
                if (buyCashAccount.getValue() != null &
                        buyEquity.getValue() != null &
                        !buyEquityAmount.getText().equals("")
                        ) {

                    CashAccount tempBuyAccount = cashAccounts.get(toAccount.getValue());
                    Equity tempBuyEquity = equitiesForSale.get(buyEquity.getValue());
                    int tempAmount = Integer.parseInt(transAmount.getText());

                    if(tempBuyAccount.getBalance() >= tempBuyEquity.getSharePrice() * tempAmount & tempBuyEquity.getSharesHeld() > 0) {

                        BuyEquity equitySale = new BuyEquity(tempAmount, tempBuyAccount, tempBuyEquity, log,  innerMyPortfolio);
                        equitySale.execute();

                        buyTransactionLabel.setText("Purchase Successful");

                        buyCashAccountNameLabel.setText("      Name: " + buyCashAccount.getValue().toString());
                        buyCashAccountBalanceLabel.setText("      Balance: $" + Double.toString(cashAccounts.get(buyCashAccount.getValue()).getBalance()));
                        buyAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(buyCashAccount.getValue()).getOpenDate());
                        userData.updateLogger(log);

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

                        Transfer cashTransfer = new Transfer(tempAmount, tempToAccount, tempFromAccount, log, user);

                        cashTransfer.execute();

                        toAccountNameLabel.setText("      Name: " + tempToAccount.toString());
                        toAccountBalanceLabel.setText("      Balance: $" + Double.toString(tempToAccount.getBalance()));
                        fromAccountNameLabel.setText("      Name: " + tempFromAccount.toString());
                        fromAccountBalanceLabel.setText("      Balance: $" + Double.toString(tempFromAccount.getBalance()));
                        userData.updateLogger(log);

                        transFundsLabel.setText("Transfer Successful");

                    } else{
                        transFundsLabel.setText("Invalid Input");
                    }

                } else {
                    transFundsLabel.setText("Invalid Input");
                }
            }
        });

        returnTrans.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene4.setRoot(transactionGrid);
            }
        });

        toAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                toAccountNameLabel.setText("      Name: " + toAccount.getValue().toString());
                toAccountBalanceLabel.setText("      Balance: $" + Double.toString(cashAccounts.get(toAccount.getValue()).getBalance()));
                toAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(toAccount.getValue()).getOpenDate());
            }
        });

        fromAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                fromAccountNameLabel.setText("      Name: " + fromAccount.getValue().toString());
                fromAccountBalanceLabel.setText("      Balance: $" + Double.toString(cashAccounts.get(fromAccount.getValue()).getBalance()));
                fromAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(fromAccount.getValue()).getOpenDate());
            }
        });

        sellCashAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                sellCashAccountNameLabel.setText("      Name: " + sellCashAccount.getValue().toString());
                sellCashAccountBalanceLabel.setText("      Balance: $" + Double.toString(cashAccounts.get(sellCashAccount.getValue()).getBalance()));
                sellAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(sellCashAccount.getValue()).getOpenDate());
            }
        });

        buyCashAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                buyCashAccountNameLabel.setText("      Name: " + buyCashAccount.getValue().toString());
                buyCashAccountBalanceLabel.setText("      Balance: $" + Double.toString(cashAccounts.get(buyCashAccount.getValue()).getBalance()));
                buyAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(buyCashAccount.getValue()).getOpenDate());
            }
        });

        buyEquity.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                buyEquityNameLabel.setText("      Name: " + buyEquity.getValue().toString());
                buyEquityValueLabel.setText("      Value: $" + availableAssets.get(buyEquity.getValue().toString()).getSharePrice());
                buyEquityOwnedLabel.setText("      Amount Owned: " );
            }
        });

        sellEquity.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                sellEquityNameLabel.setText("      Name: " + sellEquity.getValue().toString());
                sellEquityValueLabel.setText("      Value: $" + Double.toString(equitiesOwned.get(sellEquity.getValue()).getSharePrice()));
                sellEquityOwnedLabel.setText("      Amount Owned: " + Double.toString(equitiesOwned.get(sellEquity.getValue()).getSharesHeld()));
            }
        });

        //PORTFOLIO NAVIGATION START
        final Button portfolioButton = new Button("Go to Portfolio");

        portfolioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioScene(mainStage, user);
            }
        });

        HBox simBox = new HBox();
        simBox.setAlignment(Pos.TOP_LEFT);
        transactionGrid.add(portfolioButton, 1, 150);
        //PORTFOLIO NAVIGATION END

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
        box2Trans.getChildren().add(fromAccountOpenDateLabel);

        box3Trans.getChildren().add(toAccountLabel);
        box3Trans.getChildren().add(toAccount);
        box3Trans.getChildren().add(toAccountNameLabel);
        box3Trans.getChildren().add(toAccountBalanceLabel);
        box3Trans.getChildren().add(toAccountOpenDateLabel);

        box4Trans.getChildren().add(amountLabel);
        box4Trans.getChildren().add(transAmount);

        box5Trans.getChildren().add(transFunds);

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
        box3Buy.getChildren().add(buyAccountOpenDateLabel);

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
        box3Sell.getChildren().add(sellAccountOpenDateLabel);

        box4Sell.getChildren().add(sellAmountLabel);
        box4Sell.getChildren().add(sellEquityAmount);

        box5Sell.getChildren().add(sellEquityButton);

        transactionGrid.add(box1Sell, 100 , 10);
        transactionGrid.add(box2Sell, 100 , 20);
        transactionGrid.add(box3Sell, 100 , 40);
        transactionGrid.add(box4Sell, 100 , 60);
        transactionGrid.add(box5Sell, 100 , 120);

        window.setScene(scene4);
        window.show();
    }

    public void simulationScene (final Stage mainStage){
        window = mainStage;
        window.setTitle("Market simulation");



//        System.out.println("IN GUI CODE");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene3 = new Scene(grid, 350, 450);

        //TRANSACTION NAVIGATION START
        final Button transactionButton = new Button("Go to Transactions");

        transactionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                transactionScene(mainStage, user);
            }
        });

        HBox transBox = new HBox();
        transBox.setAlignment(Pos.TOP_LEFT);
        grid.add(transactionButton, 1, 200);
        //TRANSACTION NAVIGATION END

        final Button portButton = new Button("Go to Portfolio");

        portButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioScene(window, user);
            }
        });

        HBox simBox = new HBox();
        simBox.setAlignment(Pos.TOP_LEFT);
        grid.add(portButton, 1, 150);

        final Label interval = new Label("Time interval: (d,m,y)");
        grid.add(interval,0,1);
        final TextField IntervalField = new TextField();
        grid.add(IntervalField, 1, 1);

        final Label steps = new Label("Number of steps: ");
        grid.add(steps, 0, 30);
        final TextField stepField = new TextField();
        grid.add(stepField, 1, 30);

        final Label percent = new Label("Equity Percentage (%): ");
        grid.add(percent, 0, 60);
        final TextField percentage = new TextField();
        grid.add(percentage, 1, 60);


        Button bullSimulation = new Button("bull market");
        HBox box3 = new HBox(10);
        box3.setAlignment(Pos.BOTTOM_LEFT);
        box3.getChildren().add(bullSimulation);
        grid.add(box3, 0, 100);

        Button bearSimulation = new Button("bear");
        HBox box = new HBox(10);
        box.setAlignment(Pos.BASELINE_CENTER);
        box.getChildren().add(bearSimulation);
        grid.add(box, 1, 100);

        Button noSimulation = new Button("No growth");
        HBox box5 = new HBox(10);
//        box5.setAlignment(Pos.BASELINE_LEFT);
        box5.getChildren().add(noSimulation);
        grid.add(box5, 0, 120);

        Button reset = new Button("Reset");
        HBox box4 = new HBox(10);
        box4.setAlignment(Pos.BOTTOM_RIGHT);
        box4.getChildren().add(reset);
        grid.add(box4, 1, 120);

        Label Value = new Label("Portfolio Value: ");
        grid.add(Value, 0, 125);
        TextField Val = new TextField();
        portValue = Val;
        grid.add(Val, 1, 125);



//        bullSimulation.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
////                ArrayList<Equity> equities = new ArrayList<Equity>();
////                ArrayList<CashAccount> cash = new ArrayList<CashAccount>();
//                MarketSimulation bull = new BullMarket();
//
//
//                List<Portfolio> portList = userData.listOfPortfolio();
//                Portfolio port = portList.get(0);
//                for (Portfolio p : portList) {
//                    if (p.getUserID().equals(user)){
//                        port = p;
//                    }
//                }
//
//
//                float tempPercent = Float.parseFloat(percentage.getText());
//                int tempSteps = Integer.parseInt(stepField.getText());
//                String tempInterval = IntervalField.getText();
//
//
//                port.setEquities(bull.runSimulation(tempPercent, port, true, tempSteps, tempInterval));
//                System.out.println(String.valueOf(port.getTotalHoldings() + " total value of PORTFOLIO"));
//                port.calculateTotalHoldings();
//                portValue.setText(String.valueOf(port.getTotalHoldings()));
//                          }
//
//        });
//
//        bearSimulation.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                MarketSimulation bear = new BearMarket();
//
//                float tempPercent = Float.parseFloat(percentage.getText());
//                int tempSteps = Integer.parseInt(stepField.getText());
//                String tempInterval = IntervalField.getText();
//                ArrayList<Equity> equities = new ArrayList<Equity>();
//                ArrayList<CashAccount> cash = new ArrayList<CashAccount>();
//                Portfolio port = new Portfolio("njb5081",equities,cash);
////                Equity eq = new Equity("t", "test", "id1", "sec1", 3, 50);
////                eq.setSharePrice(30);
////                Equity eq2 = new Equity("t2", "test1", "id2", "sec4", 1, 100);
////                eq2.setSharePrice(10);
//                port.addEquity("t",3,50,"3/10/16",false);
//                for (Equity pfkn : port.getportfolioEquity()){
//                    System.out.println(pfkn.getSharePrice() + "equities in portfolio");
//                }
////                for (Equity EEE : equities) {
////                    System.out.println(EEE.EquityPrice + " before simulation");
////                }
//                port.setEquities(bear.runSimulation(tempPercent, port, true, tempSteps, tempInterval));
//                for (Equity pfkn : port.getportfolioEquity()){
//                    System.out.println(pfkn.getSharePrice() + "equities in portfolio after simulation");
//                }
//                port.calculateTotalHoldings();
//                portValue.setText(String.valueOf(port.getTotalHoldings()));
//                for (Equity ppp : port.getportfolioEquity()) {
//                    System.out.println(ppp.getSharePrice() + "After sim");
//                }
//                //todo RESET FUNCTION NOT WORKING CORRECTLY
//                // tempList = bear.reset(equities);
//                //todo Calling reset on wrong thing should be portfolio???
//
//
//            }
//
//        });

        window.setScene(scene3);
        window.show();
    }

    public void portfolioScene(final Stage stage, final String userID){
        window = stage;
        window.setTitle("My Portfolio");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Scene portScene = new Scene(grid, 500, 500);

        List<Portfolio> portList = userData.listOfPortfolio();
        Portfolio myPortfolio = portList.get(0);
        for (Portfolio p : portList) {
            if (p.getUserID().equals(userID)){
                myPortfolio = p;
            }
        }

        int i = 0;
        Label welcome = new Label("Welcome,  " + myPortfolio.getUserID());
        grid.add(welcome, 0, i);
        //Label userName = new Label(myPortfolio.getUserID());
        //grid.add(userName, 1, i);
        i++;

        double totalMoney = 0;
        for (CashAccount c : myPortfolio.getCashAccounts()) {
            totalMoney += (c.getBalance());
        }
        Label total = new Label("Total Account Balance:  " + String.valueOf(totalMoney));
        grid.add(total, 0, i);
        //Label totalDesc = new Label(String.valueOf(totalMoney));
        //grid.add(totalDesc, 1, i);
        i++;

        //LOGGER NAVIGATION START
        final Button logButton = new Button("Go to Logger");

        logButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loggerScene(stage);
            }
        });

        HBox logBox = new HBox();
        logBox.setAlignment(Pos.TOP_LEFT);
        grid.add(logButton, 1, 300);
        //LOGGER NAVIGATION END

        //TRANSACTION NAVIGATION START
        final Button transactionButton = new Button("Go to Transactions");

        transactionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                transactionScene(stage);
            }
        });

        HBox transBox = new HBox();
        transBox.setAlignment(Pos.TOP_LEFT);
        grid.add(transactionButton, 0, i);
        //TRANSACTION STUFF END
        grid.add(transactionButton, 1, 200);
        //TRANSACTION NAVIGATION END

//        for (CashAccount c : myPortfolio.getCashAccounts()){
//            Label name = new Label("Account Name: ");
//            grid.add(name, 0, i);
//            Label nameDesc = new Label(c.toString());
//            grid.add(nameDesc, 1, i);
//            i++;
//            Label bal = new Label("Balance: ");
//            grid.add(bal, 0, i);
//            Label balDesc = new Label(String.valueOf(c.getBalance()));
//            grid.add(balDesc, 1, i);
//            i++;
//        }

        Button marketSimulation = new Button("MarketSimulation");
        grid.add(marketSimulation, 1, i);
        marketSimulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simulationScene(window);
            }
        });
        i++;

        Button addAccount = new Button("Add a Cash Account");
        addAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addCashAccountScene(window, userID);
            }
        });

        grid.add(addAccount, 0, i);
        Button deleteAccount = new Button("Delete a Cash Account");
        deleteAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteCashAccountScene(window);
            }
        });
        grid.add(deleteAccount, 1, i);

        window.setScene(portScene);
        window.show();
    }

    public void addCashAccountScene(Stage stage, final String userid){
        window = stage;
        window.setTitle("Add a New Cash Account");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Scene sceneAddAcc = new Scene(grid, 500, 500);

        final Label accName = new Label("Enter Account Name:");
        grid.add(accName, 0,0);
        final TextField nameField = new TextField();
        grid.add(nameField,1,0);
        final Label accAmount = new Label("Enter Account Balance: (format: $$$.$$)");
        grid.add(accAmount, 0, 1);
        final TextField amountField = new TextField();
        grid.add(amountField, 1, 1);

        Button cancelB = new Button("Cancel");
        cancelB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioScene(window, userid);
            }
        });
        grid.add(cancelB, 0, 3);

        Button addAcc = new Button("Add Account");
        addAcc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String amount = amountField.getText();
                String name = nameField.getText();
                //double balance = Double.parseDouble(amount);
                //double balance = Double.parseDouble(amount);
                Double balance = Double.parseDouble(amount);

                //CashAccount acc = new CashAccount(balance, name);
                //Find correct portfolio in list of portfolios from text file
                //Should i just pass the portfolio object into the scene method instead of userid?
                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolio = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(userid)){
                        myPortfolio = p;
                    }
                }

                int hold = 0;

                for(int i = 0; i < myPortfolio.getCashAccounts().size(); i++){
                    if(myPortfolio.getCashAccounts().get(i).toString().equals(name)){
                        myPortfolio.getCashAccounts().get(i).addFunds(balance);
                        userData.updatePortfolioList(portList);
                        portfolioScene(window, userid);
                        hold = 1;
                    }
                }
                if(hold == 0) {
                    myPortfolio.addCashAccount(name, balance);
                    userData.updatePortfolioList(portList);
                    portfolioScene(window, userid);
                }

            }
        });
        grid.add(addAcc, 1, 3);

        window.setScene(sceneAddAcc);
        window.show();
    }

    public void deleteCashAccountScene(Stage stage) {
        window = stage;
        window.setTitle("Delete an Account");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Scene deleteAccScene = new Scene(grid, 500, 500);

        Label select = new Label("Select an account to delete: ");
        grid.add(select, 0, 0);

        List<Portfolio> portList = userData.listOfPortfolio();
        Portfolio myPortfolio = portList.get(0);
        for (Portfolio p : portList) {
            if (p.getUserID().equals(user)){
                myPortfolio = p;
            }
        }
        final Portfolio port = myPortfolio;
        final HashMap<String, CashAccount> cashAccounts = new HashMap<String, CashAccount>();
        for (CashAccount c : myPortfolio.getCashAccounts()) {
            cashAccounts.put(c.toString(), c);
        }
        final ObservableList<String> optionsAccounts = FXCollections.observableArrayList();
        optionsAccounts.addAll(cashAccounts.keySet());
        final ComboBox account = new ComboBox(optionsAccounts);
        grid.add(account, 1, 0);

        Button confirm = new Button("Delete Account");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //port.deleteCashAccount(account.getValue().toString());

                //userData.updatePortfolioList();

                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolio = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        myPortfolio = p;
                    }
                }
                myPortfolio.deleteCashAccount(account.getValue().toString());
                userData.updatePortfolioList(portList);
                portfolioScene(window, user);
            }
        });
        grid.add(confirm, 1, 1);


        window.setScene(deleteAccScene);
        window.show();

    }


    public static void main(String[] args) {

        //Check for admin command line input.
        if(args.length>0) {
            if (args[0].equals("-delete")) {
                for (User u : userData.listOfUser()) {
                    if (u.username().equals(args[1])) {
                        //delete the user from the application.
                        userData.deleteUserAccount(u.username());
                    }
                }
            }
        }
        launch(args);
    }
}
