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

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;


public class Main extends Application {

    private Logger log = new Logger();
    Stage window;
    Scene scene1, scene2, scene3, scene4;
    static data userData = new data();

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
        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                if (pwBox.getText().equals(confirmPw.getText())){

                    User newAccount = new User(userField.getText(),pwBox.getText());
                    if(!userData.isUserExist(newAccount)) {
                        userData.saveAccount(newAccount);
                        message.setText("register success");
                    } else {
                        message.setText("Account has been created");
                    }

                } else {

                    message.setText("please confirm the password");

                }
            }
        });
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

    public void transactionScene(final Stage mainStage){

        window = mainStage;
        window.setTitle("Transactions");

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
        final HashMap<String, Equity> equities =  new HashMap<String, Equity>();

        final ObservableList<String> optionsCashAccounts = FXCollections.observableArrayList();
        final ObservableList<String> optionsEquities = FXCollections.observableArrayList();

        optionsCashAccounts.addAll(cashAccounts.keySet());
        optionsEquities.addAll(equities.keySet());

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
        final Button newCashAccount = new Button("Add Cash Account");

        final Label newCashAccountLabel = new Label("Enter New Cash Account Details");
        final Label newCashAccountNameLabel = new Label("Name of New Cash Account: ");
        final Label newCashAccountBalanceLabel = new Label("Balance of New Cash Account: ");

        final TextField newCashAccountName = new TextField();
        final NumberTextField newCashAccountBalance = new NumberTextField();

        final ComboBox sellCashAccount = new ComboBox(optionsCashAccounts);
        final ComboBox sellEquity = new ComboBox(optionsEquities);

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
        final ComboBox buyEquity = new ComboBox(optionsEquities);

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

                        sellCashAccountNameLabel.setText("      Name: " + sellCashAccount.getValue().toString());
                        sellCashAccountBalanceLabel.setText("      Balance: $" + Double.toString(cashAccounts.get(sellCashAccount.getValue()).getBalance()));
                        sellAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(sellCashAccount.getValue()).getOpenDate());

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

                    if(tempBuyAccount.getBalance() >= tempBuyEquity.getSharePrice() * tempAmount & tempBuyEquity.getSharesHeld() > 0) {

                        BuyEquity equitySale = new BuyEquity(tempAmount, tempBuyAccount, tempBuyEquity, log);
                        equitySale.execute();

                        buyTransactionLabel.setText("Purchase Successful");

                        buyCashAccountNameLabel.setText("      Name: " + buyCashAccount.getValue().toString());
                        buyCashAccountBalanceLabel.setText("      Balance: $" + Double.toString(cashAccounts.get(buyCashAccount.getValue()).getBalance()));
                        buyAccountOpenDateLabel.setText("      Open Date: " + cashAccounts.get(buyCashAccount.getValue()).getOpenDate());

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

                        toAccountNameLabel.setText("      Name: " + tempToAccount.toString());
                        toAccountBalanceLabel.setText("      Balance: $" + Double.toString(tempToAccount.getBalance()));
                        fromAccountNameLabel.setText("      Name: " + tempFromAccount.toString());
                        fromAccountBalanceLabel.setText("      Balance: $" + Double.toString(tempFromAccount.getBalance()));

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
                scene4.setRoot(createCashAccountGrid);
            }
        });

        returnTrans.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene4.setRoot(transactionGrid);
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
                buyEquityValueLabel.setText("      Value: $" + Double.toString(equities.get(buyEquity.getValue()).getSharePrice()));
                buyEquityOwnedLabel.setText("      Amount Owned: " + Double.toString(equities.get(buyEquity.getValue()).getSharesHeld()));
            }
        });

        sellEquity.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                sellEquityNameLabel.setText("      Name: " + sellEquity.getValue().toString());
                sellEquityValueLabel.setText("      Value: $" + Double.toString(equities.get(sellEquity.getValue()).getSharePrice()));
                sellEquityOwnedLabel.setText("      Amount Owned: " + Double.toString(equities.get(sellEquity.getValue()).getSharesHeld()));
            }
        });

        //SIMULATION STUFF START
        final Button simulationButton = new Button("Go to Simulation");

        simulationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simulationScene(mainStage);
            }
        });

        HBox simBox = new HBox();
        simBox.setAlignment(Pos.TOP_LEFT);
        transactionGrid.add(simulationButton, 1, 150);
        //SIMULATION STUFF END

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

        //TRANSACTION STUFF START
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
        //TRANSACTION STUFF END

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


        Button simulation = new Button("bull market");
        HBox box3 = new HBox(10);
        box3.setAlignment(Pos.BOTTOM_LEFT);
        box3.getChildren().add(simulation);
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

        final Label Value = new Label("Portfolio Value: ");
        grid.add(Value, 0, 125);
        final TextField Val = new TextField();
        grid.add(Val, 1, 125);



        simulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MarketSimulation bull = new BullMarket();

                float tempPercent = Float.parseFloat(percentage.getText());
                int tempSteps = Integer.parseInt(stepField.getText());
                String tempInterval = IntervalField.getText();
                ArrayList<Equity> equities = new ArrayList<Equity>();
                Equity eq = new Equity("t","test","id1","sec1",3,50);
//                eq.setSharePrice(30);
                Equity eq2 = new Equity("t2","test1","id2","sec4",1,100);
//                eq2.setSharePrice(10);
                equities.add(eq);
//                equities.add(eq2);
//                for (Equity EEE : equities) {
//                    System.out.println(EEE.EquityPrice + " before simulation");
//                }
                ArrayList<Equity> tempList = new ArrayList<Equity>();
                equities = bull.runSimulation(tempPercent, equities, true, tempSteps, tempInterval);
                for (Equity ppp : equities){
                    System.out.println(ppp.getSharePrice() + "AFter sim");
                }
                //todo RESET FUNCTION NOT WORKING CORRECTLY
                // tempList = bull.reset(equities);
                //todo Calling reset on wrong thing should be portfolio???
                for (Equity EEE : tempList) {
                    System.out.println(EEE.getSharePrice() + " After reset");
                }

            }

        });

        bearSimulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MarketSimulation bear = new BearMarket();

                float tempPercent = Float.parseFloat(percentage.getText());
                int tempSteps = Integer.parseInt(stepField.getText());
                String tempInterval = IntervalField.getText();
                ArrayList<Equity> equities = new ArrayList<Equity>();
                Equity eq = new Equity("t", "test", "id1", "sec1", 3, 50);
//                eq.setSharePrice(30);
                Equity eq2 = new Equity("t2", "test1", "id2", "sec4", 1, 100);
//                eq2.setSharePrice(10);
                equities.add(eq);
                equities.add(eq2);
//                for (Equity EEE : equities) {
//                    System.out.println(EEE.EquityPrice + " before simulation");
//                }
                ArrayList<Equity> tempList = new ArrayList<Equity>();
                equities = bear.runSimulation(tempPercent, equities, true, tempSteps, tempInterval);
                for (Equity ppp : equities) {
                    System.out.println(ppp.getSharePrice() + "After sim");
                }
                //todo RESET FUNCTION NOT WORKING CORRECTLY
                // tempList = bear.reset(equities);
                //todo Calling reset on wrong thing should be portfolio???
                for (Equity EEE : tempList) {
                    System.out.println(EEE.getSharePrice() + " After reset");
                }

            }

        });

        window.setScene(scene3);
        window.show();
    }

    public void portfolioScene(Stage stage, final String userID){
        window = stage;
        window.setTitle("My Portfolio");

        //System.out.println("Portfolio Scene");
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
        Label userName = new Label(myPortfolio.getUserID());
        grid.add(userName, 0, 0);

        Button addAccount = new Button("Add a Cash Account");

        addAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //String user = userID;
                addCashAccountScene(window, userID);
            }
        });

//        bearSimulation.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
        grid.add(addAccount, 0, 1);

        window.setScene(portScene);
        window.show();
    }

    public void addCashAccountScene(Stage stage, final String userid){
        window = stage;
        window.setTitle("Add a New Cash Account");

//        final Label username = new Label("username");
//        grid.add(username, 0, 1);
//        final TextField userField = new TextField();
//        grid.add(userField, 1, 1);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Scene sceneAddAcc = new Scene(grid, 500, 500);

        final Label accName = new Label("Enter Account Name:");
        grid.add(accName, 0,0);
        final TextField nameField = new TextField();
        grid.add(nameField,1,0);
        final Label accAmount = new Label("Enter Account Balance:");
        grid.add(accAmount, 0, 1);
        final TextField amountField = new TextField();
        grid.add(amountField, 1, 1);

        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        //System.out.println(date);

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
                int balance = Integer.parseInt(amount);
                CashAccount acc = new CashAccount(balance, name);
                //Find correct portfolio in list of portfolios from text file
                //Should i just pass the portfolio object into the scene method instead of userid?
                List<Portfolio> portList = userData.listOfPortfolio();
                Portfolio myPortfolio = portList.get(0);
                for (Portfolio p : portList) {
                    if (p.getUserID().equals(userid)){
                        myPortfolio = p;
                    }
                }


            }
        });
        grid.add(addAcc, 1, 3);

        window.setScene(sceneAddAcc);
        window.show();
    }


    public void addEquityScene(Stage stage, String userid){

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
