package sample.GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import sample.*;
import sample.Holdings.Asset;
import sample.Holdings.CashAccount;
import sample.Holdings.Equity;
import sample.Holdings.MarketAverage;
import sample.Logger.Entry;
import sample.Logger.Logger;
import sample.Transactions.BuyEquity;
import sample.Transactions.SellEquity;
import sample.Transactions.Transfer;
import sample.handleData.data;
import sample.handleData.handleEquity;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import sample.GUI.accountHandler;
/*
* This class will display all the GUI for the system
* */

public class Main extends Application {

    /*
    * Support  variable
    * */
    Stage window;
    /*
    * define multiple scene to display different GUI
    * */
    Scene  scene1,scene2,scene3, scene4, scene5;
    /*
    * Initialize the data class to access different function in order to creeate,save, delete account
    * */
    static data userData = new data();
    static handleEquity equityHandler = new handleEquity();
    static accountHandler accountHandle = new accountHandler();
    static portfolioHandler portfolioHandle = new portfolioHandler();
    TextField portValue;
    String user;
    Portfolio port;
    Portfolio tempPort;
    MarketSimulation marketSim;
    private Logger log = userData.getLog();

    Map<String, List<String>> indexMap = equityHandler.getIndexMap();
    Map<String, Equity> equityMap = equityHandler.getEquityMap();

    //Starts the initial JavaFX GUI
    @Override
    public void start(Stage primaryStage) throws Exception{
        accountHandle.loginScene(primaryStage);
    }

    /**
     * Shows the first screen that allows a user to login to a portfolio
     * @param mainStage
     */


//    public void loginScene(Stage mainStage){
//        window = mainStage;
//        window.setTitle("Login page");
//
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(15);
//        grid.setVgap(15);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//
//        scene1 = new Scene(grid, 300, 300);
//
//
//
//        Text sceneTitle = new Text("welcome");
//        sceneTitle.setFont(Font.font("Arial"));
//        grid.add(sceneTitle, 0 , 0 ,2 ,1);
//
//        //user input
//        final Label username = new Label("username");
//        grid.add(username, 0, 1);
//        final TextField userField = new TextField();
//        grid.add(userField, 1, 1);
//        final Label password = new Label("password");
//        grid.add(password, 0, 2);
//        final PasswordField pwBox = new PasswordField();
//        grid.add(pwBox, 1, 2);
//
//        final Text message = new Text();
//        grid.add(message, 1, 7);
//
//        //add button login
//        Button login = new Button("Sign In");
//        HBox box = new HBox(10);
//        box.setAlignment(Pos.BOTTOM_RIGHT);
//
//        //action for button
//        login.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                User loginUser = new User(userField.getText(),pwBox.getText());
//                user = userField.getText();
//                //if the user account has been created, then the user can login
//                if(userData.isUserExist(loginUser)){
//                    message.setFill(Color.FIREBRICK);
//                    message.setText("successful sign in");
//                    portfolioScene(window, loginUser.username());
//                } else {
//
//                    message.setText("Please enter correct information");
//
//                }
//            }
//        });
//        box.getChildren().add(login);
//        grid.add(box, 1 , 4);
//
//        //add register account
//        Label noAccount = new Label("need new Account?");
//        noAccount.autosize();
//        grid.add(noAccount, 0, 5);
//        Button register = new Button("Register");
//
//        //action
//        register.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                registerScene(window);
//            }
//        });
//
//        HBox box2 = new HBox(10);
//        box2.setAlignment(Pos.BOTTOM_RIGHT);
//        box2.getChildren().add(register);
//        grid.add(box2, 1, 5);
//        window.setScene(scene1);
//        window.show();
//    }
//
//    /**
//     * Shows the page that allows for registering new users/portfolios
//     * @param mainStage
//     */
//    public void registerScene(Stage mainStage){
//        //scene 2 register
//        window = mainStage;
//        window.setTitle("Register page");
//        //window.setScene(scene2);
//        GridPane grid2 = new GridPane();
//        grid2.setAlignment(Pos.CENTER);
//        grid2.setHgap(15);
//        grid2.setVgap(15);
//        grid2.setPadding(new Insets(25, 25, 25, 25));
//
//        scene2 = new Scene(grid2, 300, 300);
//
//        Text registerSceneTitle = new Text("");
//        registerSceneTitle.setFont(Font.font("Arial"));
//        grid2.add(registerSceneTitle, 0 , 0 ,2 ,1);
//
//        //user input
//        Label username = new Label("username");
//        grid2.add(username, 0, 1);
//        final TextField userField = new TextField();
//        grid2.add(userField, 1, 1);
//        final Label password = new Label("password");
//        grid2.add(password, 0, 2);
//        final PasswordField pwBox = new PasswordField();
//        grid2.add(pwBox, 1, 2);
//        Label confirm = new Label("confirm password");
//        grid2.add(confirm, 0, 3);
//        final PasswordField confirmPw = new PasswordField();
//        grid2.add(confirmPw, 1, 3);
//
//        final Text message = new Text();
//        grid2.add(message, 1, 7);
//
//        //add button login
//        Button register = new Button("Sign Up");
//        HBox box = new HBox(10);
//        box.setAlignment(Pos.BOTTOM_RIGHT);
//
////        action for button
//        register.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                //confirm the password for registation
//                if (pwBox.getText().equals(confirmPw.getText())){
//                    User newAccount = new User(userField.getText(),pwBox.getText());
//                    //check whether someone has used this username or not
//                    if(!userData.usernameExist(newAccount.username())) {
//                        userData.saveAccount(newAccount);
//                        message.setText("register success");
//                    } else {
//                        message.setText("Account has been created");
//                    }
//                } else {
//                    message.setText("please confirm the password");
//
//                }
//            }
//        });
//        box.getChildren().add(register);
//        grid2.add(box, 1 , 4);
//
//        //add register account
//        Label needLogin = new Label("need Login?");
//        needLogin.autosize();
//        grid2.add(needLogin, 0, 6);
//        Button login = new Button("login");
//        //move to the login scene
//        login.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                loginScene(window);
//            }
//        });
//        grid2.add(login, 1, 6);
//        HBox box2 = new HBox(10);
//        box2.setAlignment(Pos.BOTTOM_RIGHT);
//        box2.getChildren().add(register);
//        grid2.add(box2, 1, 5);
//        window.setScene(scene2);
//        window.show();
//    }


    /**
     * Shows logs for previous actions
     * @param mainStage stage for the window
     */
    public void loggerScene(final Stage mainStage){
        window = mainStage;
        window.setTitle("Log");

        final GridPane logGrid = new GridPane();
        logGrid.setAlignment(Pos.TOP_LEFT);
        logGrid.setHgap(1);
        logGrid.setVgap(1);
        logGrid.setPadding(new Insets(25, 25, 25, 25));

        final Label logDescription = new Label("Choose Entry to View");
        final Label entryDescription = new Label("     Entry Description:");

        //PORTFOLIO NAVIGATION START
        final Button portButton = new Button("Go to Portfolio");

        portButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioHandle.portfolioScene(mainStage, user);
            }
        });
        //PORTFOLIO NAVIGATION END

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

    /**
     * Shows a page that allows transferring funds and buying and selling equities
     * @param mainStage stage for the window
     */
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

        final HashMap<String, Asset> availableAssets = new HashMap<String, Asset>();

        for(String equityName : equityMap.keySet()){

            availableAssets.put(equityName, equityMap.get(equityName));

        }

        for(String indexName : indexMap.keySet()){

            ArrayList<Equity> tempEquities = new ArrayList<Equity>();

            for(String equityName : indexMap.get(indexName)){
                tempEquities.add(equityMap.get(equityName));
            }

            Asset tempMarketAverage = new MarketAverage(indexName, tempEquities);
            availableAssets.put(tempMarketAverage.getName(), tempMarketAverage);

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

        for (int i = 0; i < myPortfolio.getCashAccounts().size(); i++){
            cashAccounts.put(myPortfolio.getCashAccounts().get(i).toString(),
                    myPortfolio.getCashAccounts().get(i));
        }

        final ObservableList<String> optionsCashAccounts = FXCollections.observableArrayList();
        final ObservableList<String> optionsAssetsAvailable = FXCollections.observableArrayList();

        optionsCashAccounts.addAll(cashAccounts.keySet());
        optionsAssetsAvailable.addAll(availableAssets.keySet());

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
        final ComboBox sellEquity = new ComboBox(optionsAssetsAvailable);

        final Label sellTransactionLabel = new Label("Choose Equity to Sell");
        final Label sellEquityNameLabel = new Label("      Name: None");
        final Label sellEquityValueLabel = new Label("      Value: None");
        final Label sellEquityOwnedLabel = new Label("      Amount Owned: 0");
        final Label sellCashAccountNameLabel = new Label("      Name: None");
        final Label sellCashAccountBalanceLabel = new Label("      Balance: None");
        final Label sellCashAccountLabel = new Label("Add funds to:");
        final Label sellEquityLabel = new Label("Sell this equity:");
        final Label sellAmountLabel = new Label("Amount of selected equity to sell:");

        final Button sellEquityButton = new Button("Sell");

        final NumberTextField sellEquityAmount = new NumberTextField();

        final ComboBox buyCashAccount = new ComboBox(optionsCashAccounts);
        final ComboBox buyEquity = new ComboBox(optionsAssetsAvailable);

        final Label buyTransactionLabel = new Label("Choose Equity to Buy");
        final Label buyEquityNameLabel = new Label("      Name: None");
        final Label buyEquityValueLabel = new Label("      Value: None");
        final Label buyEquityOwnedLabel = new Label("      Amount Owned: 0");
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

                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolioInner = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        myPortfolioInner = p;
                    }
                }

                if (sellCashAccount.getValue() != null &
                        sellEquity.getValue() != null &
                        !sellEquityAmount.getText().equals("")
                        ) {

                    CashAccount tempSellAccount = myPortfolioInner.getCashAccountByName(sellCashAccount.getValue().toString());
                    Asset tempSellEquity = availableAssets.get(sellEquity.getValue().toString());
                    int tempAmount = Integer.parseInt(sellEquityAmount.getText());

                    if(myPortfolioInner.getSharesHeld().get(tempSellEquity.getName()) >= tempAmount) {

                        SellEquity equitySale = new SellEquity(tempAmount, myPortfolioInner.getCashAccountByName(tempSellAccount.toString()), tempSellEquity, log, myPortfolioInner);
                        equitySale.execute();

                        sellTransactionLabel.setText("Sale Successful");

                        sellCashAccountNameLabel.setText("      Name: " + sellCashAccount.getValue().toString());
                        sellCashAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", tempSellAccount.getBalance()));
                        sellAccountOpenDateLabel.setText("      Open Date: " + tempSellAccount.getOpenDate());
                        sellEquityOwnedLabel.setText("      Amount Owned: " + Integer.toString(myPortfolioInner.getSharesHeld().get(tempSellEquity.getName())));
                        userData.updateLogger(log);
                        userData.updatePortfolioList(portList);

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

                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolioInner = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        myPortfolioInner = p;
                    }
                }

                //Check to make sure a cash account and equity is selected and an amount is specified
                if (buyCashAccount.getValue() != null &
                        buyEquity.getValue() != null &
                        !buyEquityAmount.getText().equals("")
                        ) {

                    CashAccount tempBuyAccount = myPortfolioInner.getCashAccountByName( buyCashAccount.getValue().toString());
                    Asset tempBuyEquity = availableAssets.get(buyEquity.getValue().toString());
                    int tempAmount = Integer.parseInt(buyEquityAmount.getText());

                    if(tempBuyAccount.getBalance() >= tempBuyEquity.getSharePrice() * tempAmount) {

                        BuyEquity equitySale = new BuyEquity(tempAmount, myPortfolioInner.getCashAccountByName(tempBuyAccount.toString()), tempBuyEquity, log,  myPortfolioInner);
                        equitySale.execute();

                        System.out.println(Integer.toString(myPortfolioInner.getSharesHeld().get(tempBuyEquity.getName())));

                        cashAccounts.put(buyCashAccount.getValue().toString(), tempBuyAccount);

                        buyTransactionLabel.setText("Purchase Successful");

                        buyCashAccountNameLabel.setText("      Name: " + buyCashAccount.getValue().toString());
                        buyCashAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", cashAccounts.get(buyCashAccount.getValue().toString()).getBalance()));
                        buyAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(buyCashAccount.getValue().toString()).getOpenDate());
                        buyEquityOwnedLabel.setText("      Amount Owned: " + Integer.toString(myPortfolioInner.getSharesHeld().get(tempBuyEquity.getName())));
                        userData.updateLogger(log);
                        userData.updatePortfolioList(portList);

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

                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolioInner = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        myPortfolioInner = p;
                    }
                }

                if (fromAccount.getValue() != null &
                        toAccount.getValue() != null &
                        toAccount.getValue() != fromAccount.getValue() &
                        !transAmount.getText().equals("")
                        ) {

                    CashAccount tempToAccount = myPortfolioInner.getCashAccountByName((toAccount.getValue().toString()));
                    CashAccount tempFromAccount = myPortfolioInner.getCashAccountByName((fromAccount.getValue().toString()));
                    int tempAmount = Integer.parseInt(transAmount.getText());

                    if(tempFromAccount.getBalance() >= tempAmount) {

                        Transfer cashTransfer = new Transfer(tempAmount, tempToAccount, tempFromAccount, log, user);

                        cashTransfer.execute();

                        toAccountNameLabel.setText("      Name: " + tempToAccount.toString());
                        toAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", tempToAccount.getBalance()));
                        fromAccountNameLabel.setText("      Name: " + tempFromAccount.toString());
                        fromAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", tempFromAccount.getBalance()));
                        userData.updateLogger(log);
                        userData.updatePortfolioList(portList);

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
                toAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", cashAccounts.get(toAccount.getValue().toString()).getBalance()));
                toAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(toAccount.getValue().toString()).getOpenDate());
            }
        });

        fromAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                fromAccountNameLabel.setText("      Name: " + fromAccount.getValue().toString());
                fromAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", cashAccounts.get(fromAccount.getValue().toString()).getBalance()));
                fromAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(fromAccount.getValue().toString()).getOpenDate());
            }
        });

        sellCashAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                sellCashAccountNameLabel.setText("      Name: " + sellCashAccount.getValue().toString());
                sellCashAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", cashAccounts.get(sellCashAccount.getValue().toString()).getBalance()));
                sellAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(sellCashAccount.getValue().toString()).getOpenDate());
            }
        });

        buyCashAccount.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                buyCashAccountNameLabel.setText("      Name: " + buyCashAccount.getValue().toString());
                buyCashAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", cashAccounts.get(buyCashAccount.getValue().toString()).getBalance()));
                buyAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(buyCashAccount.getValue().toString()).getOpenDate());
            }
        });

        buyEquity.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {

                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolioInner = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        myPortfolioInner = p;
                    }
                }

                buyEquityNameLabel.setText("      Name: " + buyEquity.getValue().toString());
                buyEquityValueLabel.setText("      Value: $" + availableAssets.get(buyEquity.getValue().toString()).getSharePrice());
                try {
                    buyEquityOwnedLabel.setText("      Amount Owned: " + Integer.toString(myPortfolioInner.getSharesHeld().get(availableAssets.get(buyEquity.getValue()).getName())));
                } catch(NullPointerException e){
                    buyEquityOwnedLabel.setText("      Amount Owned: 0");
                }
            }
        });

        sellEquity.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolioInner = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        myPortfolioInner = p;
                    }
                }

                sellEquityNameLabel.setText("      Name: " + sellEquity.getValue().toString());
                sellEquityValueLabel.setText("      Value: $" + availableAssets.get(sellEquity.getValue().toString()).getSharePrice());
                try {
                    sellEquityOwnedLabel.setText("      Amount Owned: " + Integer.toString(myPortfolioInner.getSharesHeld().get(availableAssets.get(sellEquity.getValue()).getName())));
                } catch(NullPointerException e){
                    sellEquityOwnedLabel.setText("      Amount Owned: 0");
                }
            }
        });

        //PORTFOLIO NAVIGATION START
        final Button portfolioButton = new Button("Go to Portfolio");

        portfolioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioHandle.portfolioScene(mainStage, user);
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

    /**
     * Shows the simulation page and allows for showing simulations on future holdings
     * @param mainStage stage for the window
     * @param port the portfolio being simulated
     */
    public void simulationScene (final Stage mainStage, final Portfolio port){
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
                transactionScene(mainStage);
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
                portfolioHandle.portfolioScene(window, user);
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

//        reset.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                for (Equity e : tempPort.getportfolioEquity()){
//                    System.out.println(e.getSharePrice() + " RIGHT BEFORE ATTEMPTED RESET");
//                }
//                port.setEquities(tempPort.getEquities());
//                for (Equity e : port.getportfolioEquity()){
//                    System.out.println(e.getSharePrice() + "Value of equity after reset");
//                }
//            }
//        });

        bullSimulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                ArrayList<Equity> equities = new ArrayList<Equity>();
//                ArrayList<CashAccount> cash = new ArrayList<CashAccount>();
                MarketSimulation bull = new BullMarket();


                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio port = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        port = p;
                    }
                }


                float tempPercent = Float.parseFloat(percentage.getText());
                int tempSteps = Integer.parseInt(stepField.getText());
                String tempInterval = IntervalField.getText();


                double money  = (bull.runSimulation(tempPercent, port, true, tempSteps, tempInterval));
                System.out.println(String.valueOf(port.getTotalHoldings() + " total value of PORTFOLIO"));
                money += port.getTotalHoldings();
                portValue.setText(String.valueOf(money));
                          }

        });
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

//    /**
//     * Shows the portfolio summary information after loggin in
//     * @param stage the stage for the window
//     * @param userID the id of the user logged in
//     */
//    public void portfolioScene(final Stage stage, final String userID){
//        window = stage;
//        window.setTitle("My Portfolio");
//
//        //create the grid being shown in the scene
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        Scene portScene = new Scene(grid, 500, 500);
//
//        //find user information from the user text file
//        List<Portfolio> portList = userData.listOfPortfolio();
//        Portfolio myPortfolio = portList.get(0);
//        for (Portfolio p : portList) {
//            if (p.getUserID().equals(userID)){
//                myPortfolio = p;
//                port = p;
//            }
//        }
//
//        int i = 0;
//        Label welcome = new Label("Welcome,  " + myPortfolio.getUserID());
//        grid.add(welcome, 0, i);
//        i++;
//
//        //display the total amount of money in all cash accounts
//        double totalMoney = 0;
//        for (CashAccount c : myPortfolio.getCashAccounts()) {
//            totalMoney += (c.getBalance());
//        }
//        Label total = new Label("Total Account Balance:  " + String.valueOf(totalMoney));
//        grid.add(total, 0, i);
//        i++;
//
//        //LOGGER NAVIGATION START
//        final Button logButton = new Button("Go to Logger");
//        logButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                loggerScene(stage);
//            }
//        });
//        HBox logBox = new HBox();
//        logBox.setAlignment(Pos.TOP_LEFT);
//        grid.add(logButton, 1, 300);
//        //LOGGER NAVIGATION END
//
//        //TRANSACTION NAVIGATION START
//        final Button transactionButton = new Button("Go to Transactions");
//        transactionButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                transactionScene(stage);
//            }
//        });
//        HBox transBox = new HBox();
//        transBox.setAlignment(Pos.TOP_LEFT);
//        grid.add(transactionButton, 0, i);
//        //TRANSACTION NAVIGATION END
//
//        //Create a button that leads to the simulation screen
//        Button marketSimulation = new Button("MarketSimulation");
//        grid.add(marketSimulation, 1, i);
//        marketSimulation.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                simulationScene(window, port);
//            }
//        });
//        i++;
//
//        //Create a button that leads to the page to add an account
//        Button addAccount = new Button("Add a Cash Account");
//        addAccount.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                addCashAccountScene(window, userID);
//            }
//        });
//
//        grid.add(addAccount, 0, i);
//        Button deleteAccount = new Button("Delete a Cash Account");
//        deleteAccount.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                deleteCashAccountScene(window);
//            }
//        });
//        grid.add(deleteAccount, 1, i);
//
//        window.setScene(portScene);
//        window.show();
//    }

    /**
     * Shows a screen to add an account to the portfolio
     * @param stage the stage for the window
     * @param userid the id of the user logged in
     */
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
                portfolioHandle.portfolioScene(window, userid);
            }
        });
        grid.add(cancelB, 0, 3);

        Button addAcc = new Button("Add Account");
        addAcc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String amount = amountField.getText();
                String name = nameField.getText();
                Double balance = Double.parseDouble(amount);

                //Find correct portfolio in list of portfolios from text file
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
                        portfolioHandle.portfolioScene(window, userid);
                        hold = 1;
                    }
                }
                if(hold == 0) {
                    myPortfolio.addCashAccount(name, balance);
                    userData.updatePortfolioList(portList);
                    portfolioHandle.portfolioScene(window, userid);
                }

            }
        });
        grid.add(addAcc, 1, 3);

        window.setScene(sceneAddAcc);
        window.show();
    }

    /**
     * Shows a screen to delete a cash account from the portfolio
     * @param stage the stage for the window
     */
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
                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolio = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        myPortfolio = p;
                    }
                }
                myPortfolio.deleteCashAccount(account.getValue().toString());
                userData.updatePortfolioList(portList);
                portfolioHandle.portfolioScene(window, user);
            }
        });
        grid.add(confirm, 1, 1);


        window.setScene(deleteAccScene);
        window.show();

    }

    /**
     * Launches the JavaFX GUI and allows for deleting users on the command line
     * @param args initial arguments
     */
    public static void main(String[] args) {

        //Check for admin command line input.
        if(args.length>0) {
            if (args[0].equals("-delete")) {
                for (User u : userData.listOfUser()) {
                    if (u.username().equals(args[1])) {
                        //delete the user from the application.
                        userData.deleteUserAccount(u);
                    }
                }
            }
        }
        equityHandler.parseEquityFile();
        launch(args);
    }
}