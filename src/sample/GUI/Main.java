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
import sample.Log.Entry;
import sample.Log.Logger;
import sample.Transactions.BuyEquity;
import sample.Transactions.SellEquity;
import sample.Transactions.Transfer;
import sample.Transactions.Transaction;
import sample.handleData.data;
import sample.handleData.handleEquity;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import sample.GUI.accountHandler;
import sun.jvm.hotspot.oops.Mark;
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
    Scene  scene1,scene2,scene3, sceneTransaction, sceneLog, sceneUndo, sceneViewHoldings;
    /*
    * Initialize the data class to access different function in order to creeate,save, delete account
    * */
    static data userData = new data();
    static handleEquity equityHandler = new handleEquity();
    static accountHandler accountHandle = new accountHandler();
    static portfolioHandler portfolioHandle = new portfolioHandler();
    private List<String> searchSymbolMatch;
    //
    private List<String> searchSymbolSellMatch;
    TextField portValue;
    String user;
    Portfolio port;
    Portfolio tempPort;
    MarketSimulation marketSim;

    Map<String, List<String>> indexMap = equityHandler.getIndexMap();
    Map<String, Equity> equityMap = equityHandler.getEquityMap();

    //Starts the initial JavaFX GUI
    @Override
    public void start(Stage primaryStage) throws Exception{
        accountHandle.loginScene(primaryStage);
    }

    /**
     * Shows logs for previous actions
     * @param mainStage stage for the window
     */
    public void loggerScene(final Stage mainStage){

        List<Portfolio> portList = userData.listOfPortfolio();
        Portfolio myPortfolio = portList.get(0);
        for (Portfolio p : portList) {
            if (p.getUserID().equals(user)){
                myPortfolio = p;
            }
        }

        window = mainStage;
        window.setTitle("Log");

        final GridPane logGrid = new GridPane();
        logGrid.setAlignment(Pos.TOP_LEFT);
        logGrid.setHgap(1);
        logGrid.setVgap(1);
        logGrid.setPadding(new Insets(25, 25, 25, 25));

        final Label logDescription = new Label("Choose Entry to View");
        final Label entryDescription = new Label("     Entry Description:");

        final Button portButton = new Button("Go to Portfolio");

        portButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioHandle.portfolioScene(mainStage, user);
            }
        });

        sceneLog = new Scene(logGrid, 600, 250);

        final HashMap<String, Entry> entries =  new HashMap<String, Entry>();

        for(int i = 0; i < myPortfolio.getLog().getEntries().size(); i++){

            entries.put(myPortfolio.getLog().getEntries().get(i).getDate(), myPortfolio.getLog().getEntries().get(i));

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

        window.setScene(sceneLog);
        window.show();
    }

    public void viewHoldingsScene(final Stage mainStage){

        window = mainStage;
        window.setTitle("View Holdings");

        List<Portfolio> portList = userData.listOfPortfolio();
        Portfolio myPortfolio = portList.get(0);
        for (Portfolio p : portList) {
            if (p.getUserID().equals(user)){
                myPortfolio = p;
            }
        }

        final Label cashAccountDescriptionLabel = new Label("Choose cash account to view:");
        final Label cashAccountNameLabel = new Label("      Name: None");
        final Label cashAccountBalanceLabel = new Label("      Balance: None");
        final Label cashAccountOpenDate = new Label("      Open Date: None");

        final Label equityDescriptionLabel = new Label("Choose cash account to view:");
        final Label equityNameLabel = new Label("      Name: None");
        final Label equityValueLabel = new Label("      Value: None");
        final Label equityOwnedLabel = new Label("      Amount Owned: None");

        final HashMap<String, CashAccount> cashAccounts =  new HashMap<String, CashAccount>();
        final HashMap<String, Equity> availableAssets = new HashMap<String, Equity>();

        for (int i = 0; i < myPortfolio.getCashAccounts().size(); i++){
            cashAccounts.put(myPortfolio.getCashAccounts().get(i).toString(),
                    myPortfolio.getCashAccounts().get(i));
        }

        for (String s : myPortfolio.getSharesHeld().keySet()){
            for (Equity e : equityHandler.getEquityMap().values()){
                if (s.equals(e.getName())){
                    availableAssets.put(e.getTickerSymbol(), e);
                }
            }

            for (String  a : equityHandler.getIndexMap().keySet()){

                if (s.equals(a)){

                    //availableAssets.add( MarketAverage() );

                }
            }
        }

        final ObservableList<String> optionsCashAccounts = FXCollections.observableArrayList();
        final ObservableList<String> assetsOwned = FXCollections.observableArrayList();

        optionsCashAccounts.addAll(cashAccounts.keySet());
        assetsOwned.addAll(availableAssets.keySet());

        final ComboBox cashAccountsComboBox = new ComboBox(optionsCashAccounts);

        cashAccountsComboBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                cashAccountNameLabel.setText("      Name: " + cashAccountsComboBox.getValue().toString());
                cashAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", cashAccounts.get(cashAccountsComboBox.getValue().toString()).getBalance()));
                cashAccountOpenDate.setText("      Open Date: " + cashAccounts.get(cashAccountsComboBox.getValue().toString()).getOpenDate());
            }
        });

        final ComboBox equityComboBox = new ComboBox(assetsOwned);

        equityComboBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolioInner = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        myPortfolioInner = p;
                    }
                }

                equityNameLabel.setText("      Name: " + equityComboBox.getValue().toString());
                equityValueLabel.setText("      Value: $" + availableAssets.get(equityComboBox.getValue().toString()).getSharePrice());
                try {
                    equityOwnedLabel.setText("      Amount Owned: " + Integer.toString(myPortfolioInner.getSharesHeld().get(availableAssets.get(equityComboBox.getValue().toString()).getName())));
                } catch(NullPointerException e){
                    equityOwnedLabel.setText("      Amount Owned: 0");
                }

            }
        });

        final Button portfolioButton = new Button("Go to Portfolio");

        portfolioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioHandle.portfolioScene(mainStage, user);
            }
        });

        final GridPane viewGrid = new GridPane();
        viewGrid.setAlignment(Pos.TOP_LEFT);
        viewGrid.setHgap(1);
        viewGrid.setVgap(1);
        viewGrid.setPadding(new Insets(25, 25, 25, 25));

        VBox cashAccountBox = new VBox();
        VBox equityBox = new VBox();
        HBox portfolioBox = new HBox();

        cashAccountBox.setAlignment(Pos.TOP_LEFT);
        equityBox.setAlignment(Pos.TOP_LEFT);
        portfolioBox.setAlignment(Pos.TOP_LEFT);

        cashAccountBox.getChildren().add(cashAccountDescriptionLabel);
        cashAccountBox.getChildren().add(cashAccountsComboBox);
        cashAccountBox.getChildren().add(cashAccountNameLabel);
        cashAccountBox.getChildren().add(cashAccountBalanceLabel);
        cashAccountBox.getChildren().add(cashAccountOpenDate);

        equityBox.getChildren().add(equityDescriptionLabel);
        equityBox.getChildren().add(equityComboBox);
        equityBox.getChildren().add(equityNameLabel);
        equityBox.getChildren().add(equityValueLabel);
        equityBox.getChildren().add(equityOwnedLabel);

        portfolioBox.getChildren().add(portfolioButton);

        viewGrid.add(cashAccountBox, 10, 10);
        viewGrid.add(equityBox, 100, 10);
        viewGrid.add(portfolioBox, 10, 50);

        sceneViewHoldings = new Scene(viewGrid, 600, 225);
        window.setScene(sceneViewHoldings);
        window.show();


    }

    /**
     * Shows a page that allows the user to undo the five most recent actions
     * @param mainStage stage for the window
     */
    public void undoScene(final Stage mainStage){

        window = mainStage;
        window.setTitle("Undo");

        List<Portfolio> portList = userData.listOfPortfolio();
        Portfolio myPortfolio = portList.get(0);
        for (Portfolio p : portList) {
            if (p.getUserID().equals(user)){
                myPortfolio = p;
            }
        }

        final GridPane undoGrid = new GridPane();
        undoGrid.setAlignment(Pos.TOP_LEFT);
        undoGrid.setHgap(1);
        undoGrid.setVgap(1);
        undoGrid.setPadding(new Insets(25, 25, 25, 25));

        final Label undoInstructionLabel = new Label("Choose Action to Undo");
        final Label description = new Label("None Selected");

        final ObservableList<String> undoableActions = FXCollections.observableArrayList();
        undoableActions.addAll(myPortfolio.getRecentTransactions());

        final Button portfolioButton = new Button("Go to Portfolio");

        final ComboBox chooseActionBox = new ComboBox(undoableActions);

        chooseActionBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolioInner = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        myPortfolioInner = p;
                    }

                   description.setText( myPortfolioInner.getActionByDate(chooseActionBox.getValue().toString()).returnStatus() +
                                        ": " +
                                        myPortfolioInner.getActionByDate(chooseActionBox.getValue().toString()).returnDescription() );
                }

            }
        });

        portfolioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioHandle.portfolioScene(mainStage, user);
            }
        });

        final Button undoButton = new Button("Undo Selected Action");

        undoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolioInner = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        myPortfolioInner = p;
                    }
                }
                if(chooseActionBox.getValue() != null) {

                    myPortfolioInner.getActionByDate(chooseActionBox.getValue().toString()).undo();
                    userData.updatePortfolioList(portList);
                    undoInstructionLabel.setText("Action Successfully Undone");
                    portfolioHandle.portfolioScene(mainStage, user);

                }else{
                    undoInstructionLabel.setText("Invalid Input");
                }

            }
        });

        HBox box1Undo = new HBox();
        VBox box2Undo = new VBox();
        VBox box3Undo = new VBox();
        VBox box4Undo = new VBox();
        HBox box5Undo = new HBox();

        box1Undo.setAlignment(Pos.TOP_LEFT);
        box2Undo.setAlignment(Pos.TOP_LEFT);
        box3Undo.setAlignment(Pos.TOP_LEFT);
        box4Undo.setAlignment(Pos.TOP_LEFT);
        box5Undo.setAlignment(Pos.TOP_LEFT);

        box1Undo.getChildren().add(undoInstructionLabel);

        box2Undo.getChildren().add(chooseActionBox);

        box3Undo.getChildren().add(description);

        box5Undo.getChildren().add(undoButton);
        box5Undo.getChildren().add(portfolioButton);

        undoGrid.add(box1Undo, 10, 10);
        undoGrid.add(box2Undo, 10, 20);
        undoGrid.add(box3Undo, 10, 30);
        undoGrid.add(box5Undo, 10, 40);

        sceneUndo = new Scene(undoGrid, 600, 200);
        window.setScene(sceneUndo);
        window.show();

    }

    /**
     * Shows a page that allows transferring funds and buying and selling equities
     * @param mainStage stage for the window
     */
    public void transactionScene(final Stage mainStage){

        window = mainStage;
        window.setTitle("Transactions");
        searchSymbolMatch = new ArrayList<String>();
        searchSymbolSellMatch = new ArrayList<String>();
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

        sceneTransaction = new Scene(transactionGrid, 900, 725);

        final HashMap<String, CashAccount> cashAccounts =  new HashMap<String, CashAccount>();

        for (int i = 0; i < myPortfolio.getCashAccounts().size(); i++){
            cashAccounts.put(myPortfolio.getCashAccounts().get(i).toString(),
                    myPortfolio.getCashAccounts().get(i));
        }

        final ObservableList<String> optionsCashAccounts = FXCollections.observableArrayList();
        final ObservableList<String> optionsAssetsAvailable = FXCollections.observableArrayList();
        final ObservableList<String> optionsAssetsOwned = FXCollections.observableArrayList();
        final ObservableList<String> optionSearch = FXCollections.observableArrayList();
        List<String> optionSearchList = new ArrayList<String>();
        optionSearchList.add("exact");
        optionSearchList.add("contains");
        optionSearchList.add("begin with");
        optionSearch.addAll(optionSearchList);
        final ArrayList<String> ownedEquity = new ArrayList<String>();

        for (String s : myPortfolio.getSharesHeld().keySet()){
            for (Equity e : equityHandler.getEquityMap().values()){

                if (s.equals(e.getName())){
                    ownedEquity.add( e.getTickerSymbol() );

                }
            }

            for (String  a : equityHandler.getIndexMap().keySet()){

                if (s.equals(a)){
                    ownedEquity.add( a );

                }
            }
        }

        optionsCashAccounts.addAll(cashAccounts.keySet());
        optionsAssetsOwned.addAll( searchSymbolSellMatch );
        optionsAssetsAvailable.addAll( searchSymbolMatch);


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
        final ComboBox sellEquity = new ComboBox(optionsAssetsOwned);

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

        //search for ticker symbol from the list of portfolio to sell
        final Label searchTickerSymbolSell = new Label("Enter ticker symbol");
        final Label searchEquityNameSell = new Label("Enter Equity name");
        final TextField searchTickerSell = new TextField();
        final TextField searchEquitySell = new TextField();
        final ComboBox optionSell = new ComboBox(optionSearch);
        Button searchForSell = new Button("Search");

        //search for ticker symbol
        final Label searchTickerSymbol = new Label("Enter ticker symbol");
        final Label searchEquityName = new Label("Enter Equity name");
        final TextField searchTicker = new TextField();
        final TextField searchEquity = new TextField();
        final ComboBox option = new ComboBox(optionSearch);
        Button search = new Button("Search");

        //perform action to search for ticker symbol to sell out
        //final Portfolio finalMyPortfolio = myPortfolio;
        searchForSell.setOnAction(new EventHandler<ActionEvent>() {
            private handleEquity handler;
            @Override

            public void handle(ActionEvent event) {
                String optionSearch = "";
                handler = new handleEquity();
                List<String> listOfSymbol = ownedEquity;
                if(optionSell.getValue() != null) {
                    optionSearch = (String) optionSell.getValue();
                }
                searchSymbolSellMatch = handler.searchEquity(searchTickerSell.getText().toUpperCase(),searchEquitySell.getText(),optionSearch,listOfSymbol);
                final ObservableList<String> list = FXCollections.observableArrayList();
                list.addAll(searchSymbolSellMatch);
                sellEquity.setItems(list);
            }
        });


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

        search.setOnAction(new EventHandler<ActionEvent>() {
            private handleEquity handler;
            @Override

            public void handle(ActionEvent event) {
                String optionSearch = "";
                handler = new handleEquity();
                List<String> listOfSymbol = new ArrayList<String>();
                listOfSymbol.addAll(indexMap.keySet());
                listOfSymbol.addAll(equityMap.keySet());
                if(option.getValue() != null) {
                     optionSearch = (String) option.getValue();
                }
                searchSymbolMatch = handler.searchEquity(searchTicker.getText().toUpperCase(),searchEquity.getText(),optionSearch,listOfSymbol);
                final ObservableList<String> list = FXCollections.observableArrayList();
                list.addAll(searchSymbolMatch);
                buyEquity.setItems(list);
            }
        });


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

                        SellEquity equitySale = new SellEquity(tempAmount, myPortfolioInner.getCashAccountByName(tempSellAccount.toString()), tempSellEquity, myPortfolioInner);
                        equitySale.execute();

                        sellTransactionLabel.setText("Sale Successful");

                        sellCashAccountNameLabel.setText("      Name: " + sellCashAccount.getValue().toString());
                        sellCashAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", tempSellAccount.getBalance()));
                        sellAccountOpenDateLabel.setText("      Open Date: " + tempSellAccount.getOpenDate());
                        sellEquityOwnedLabel.setText("      Amount Owned: " + Integer.toString(myPortfolioInner.getSharesHeld().get(tempSellEquity.getName())));
                        userData.updatePortfolioList(portList);
                        portfolioHandle.portfolioScene(mainStage, user);

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

                        Transaction equitySale = new BuyEquity(tempAmount, myPortfolioInner.getCashAccountByName(tempBuyAccount.toString()), tempBuyEquity,  myPortfolioInner);
                        equitySale.execute();

                        cashAccounts.put(buyCashAccount.getValue().toString(), tempBuyAccount);

                        buyTransactionLabel.setText("Purchase Successful");

                        buyCashAccountNameLabel.setText("      Name: " + buyCashAccount.getValue().toString());
                        buyCashAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", cashAccounts.get(buyCashAccount.getValue().toString()).getBalance()));
                        buyAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(buyCashAccount.getValue().toString()).getOpenDate());
                        buyEquityOwnedLabel.setText("      Amount Owned: " + Integer.toString(myPortfolioInner.getSharesHeld().get(tempBuyEquity.getName())));
                        userData.updatePortfolioList(portList);
                        portfolioHandle.portfolioScene(mainStage, user);

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

                        Transfer cashTransfer = new Transfer(tempAmount, myPortfolioInner.getCashAccountByName((toAccount.getValue().toString())),
                                                                myPortfolioInner.getCashAccountByName((fromAccount.getValue().toString())),
                                                                myPortfolioInner);
                        cashTransfer.execute();

                        toAccountNameLabel.setText("      Name: " + tempToAccount.toString());
                        toAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", tempToAccount.getBalance()));
                        fromAccountNameLabel.setText("      Name: " + tempFromAccount.toString());
                        fromAccountBalanceLabel.setText("      Balance: $" + String.format("%.2f", tempFromAccount.getBalance()));
                        userData.updatePortfolioList(portList);

                        transFundsLabel.setText("Transfer Successful");
                        portfolioHandle.portfolioScene(mainStage, user);

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
                sceneTransaction.setRoot(transactionGrid);
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
                buyEquityValueLabel.setText("      Value: $" + String.format("%.2f", availableAssets.get(buyEquity.getValue().toString()).getSharePrice()));
                try {
                    buyEquityOwnedLabel.setText("      Amount Owned: " + Integer.toString(myPortfolioInner.getSharesHeld().get(availableAssets.get(buyEquity.getValue().toString()).getName())));
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
                sellEquityValueLabel.setText("      Value: $" + String.format("%.2f", availableAssets.get(sellEquity.getValue().toString()).getSharePrice()));
                try {
                    sellEquityOwnedLabel.setText("      Amount Owned: " + Integer.toString(myPortfolioInner.getSharesHeld().get(availableAssets.get(sellEquity.getValue().toString()).getName())));
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

        VBox boxSearchBuy = new VBox();
        HBox box1Buy = new HBox();
        VBox box2Buy = new VBox();
        VBox box3Buy = new VBox();
        VBox box4Buy = new VBox();
        HBox box5Buy = new HBox();

        boxSearchBuy.getChildren().add(searchTickerSymbol);
        boxSearchBuy.getChildren().add(searchTicker);
        boxSearchBuy.getChildren().add(searchEquityName);
        boxSearchBuy.getChildren().add(searchEquity);
        boxSearchBuy.getChildren().add(search);
        boxSearchBuy.getChildren().add(option);

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

        transactionGrid.add(boxSearchBuy, 200, 1);
        transactionGrid.add(box1Buy, 200 , 10);
        transactionGrid.add(box2Buy, 200 , 20);
        transactionGrid.add(box3Buy, 200 , 40);
        transactionGrid.add(box4Buy, 200 , 60);
        transactionGrid.add(box5Buy, 200 , 120);

        VBox boxSearchSell = new VBox();
        HBox box1Sell = new HBox();
        VBox box2Sell = new VBox();
        VBox box3Sell = new VBox();
        VBox box4Sell = new VBox();
        HBox box5Sell = new HBox();

        boxSearchSell.getChildren().add(searchTickerSymbolSell);
        boxSearchSell.getChildren().add(searchTickerSell);
        boxSearchSell.getChildren().add(searchEquityNameSell);
        boxSearchSell.getChildren().add(searchEquitySell);
        boxSearchSell.getChildren().add(searchForSell);
        boxSearchSell.getChildren().add(optionSell);

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

        transactionGrid.add(boxSearchSell, 100, 1);
        transactionGrid.add(box1Sell, 100 , 10);
        transactionGrid.add(box2Sell, 100 , 20);
        transactionGrid.add(box3Sell, 100 , 40);
        transactionGrid.add(box4Sell, 100 , 60);
        transactionGrid.add(box5Sell, 100 , 120);

        window.setScene(sceneTransaction);
        window.show();
    }

    /**
     * Shows the simulation page and allows for showing simulations on future holdings
     * @param mainStage stage for the window
     * @param port the portfolio being simulated
     */
    public void simulationScene (final Stage mainStage, final Portfolio port){
        window = mainStage;
        window.setTitle("Market Simulation");



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


        Button bullSimulation = new Button("Bull Market");
        HBox box3 = new HBox(10);
        box3.setAlignment(Pos.BOTTOM_LEFT);
        box3.getChildren().add(bullSimulation);
        grid.add(box3, 0, 100);

        Button bearSimulation = new Button("Bear Market");
        HBox box = new HBox(10);
        box.setAlignment(Pos.BASELINE_CENTER);
        box.getChildren().add(bearSimulation);
        grid.add(box, 1, 100);

        Button noSimulation = new Button("No-Growth Market");
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

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio port = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(user)){
                        port = p;
                    }
                }
                MarketSimulation bullReset = new BullMarket();
                double money = bullReset.reset(port);
                portValue.setText(String.valueOf(money));
            }
        });

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
                portValue.setText(String.valueOf(money));
                          }

        });
//
        bearSimulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                ArrayList<Equity> equities = new ArrayList<Equity>();
//                ArrayList<CashAccount> cash = new ArrayList<CashAccount>();
                MarketSimulation bull = new BearMarket();


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
                portValue.setText(String.valueOf(money));
            }

        });

        noSimulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                ArrayList<Equity> equities = new ArrayList<Equity>();
//                ArrayList<CashAccount> cash = new ArrayList<CashAccount>();
                MarketSimulation noGrowth = new NoGrowthMarket();


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


                double money  = (noGrowth.runSimulation(tempPercent, port, true, tempSteps, tempInterval));
                portValue.setText(String.valueOf(money));
            }

        });

        window.setScene(scene3);
        window.show();
    }

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
        Scene deleteAccScene = new Scene(grid, 350, 200);

        Label select = new Label("Select an account to delete: ");
        grid.add(select, 10, 0);

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
        grid.add(account, 20, 0);

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
                myPortfolio.getCashAccountByName(account.getValue().toString()).update();
                myPortfolio.deleteCashAccount(account.getValue().toString());
                userData.updatePortfolioList(portList);
                portfolioHandle.portfolioScene(window, user);
            }
        });
        grid.add(confirm, 20, 5);

        Button toPortfolio = new Button("Go to Portfolio");
        toPortfolio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioHandle.portfolioScene(window, user);
            }
        });
        grid.add(toPortfolio, 20, 7 );


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
        //equityHandler.searchEquity("FO","","begin with");
        equityHandler.parseEquityFile();
        //equityHandler.updateSharePrice();
        //equityHandler.updateSharePriceTimer(10);
        launch(args);
    }
}