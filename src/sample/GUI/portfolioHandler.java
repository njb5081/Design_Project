package sample.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Holdings.CashAccount;
import sample.Logger.Logger;
import sample.Portfolio;
import sample.handleData.data;

import java.util.List;

/**
 * Created by minhduong on 4/4/16.
 */
public class portfolioHandler {
    private Logger log = userData.getLog();
    Stage window;
    String user;
    Portfolio port;
    Portfolio tempPort;
    static data userData = new data();
    static Main main = new Main();
    /**
     * Shows the portfolio summary information after loggin in
     * @param stage the stage for the window
     * @param userID the id of the user logged in
     */
    public void portfolioScene(final Stage stage, final String userID){
        window = stage;
        window.setTitle("My Portfolio");

        //create the grid being shown in the scene
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Scene portScene = new Scene(grid, 500, 500);

        //find user information from the user text file
        List<Portfolio> portList = userData.listOfPortfolio();
        Portfolio myPortfolio = portList.get(0);
        for (Portfolio p : portList) {
            if (p.getUserID().equals(userID)){
                myPortfolio = p;
                port = p;
            }
        }

        int i = 0;
        Label welcome = new Label("Welcome,  " + myPortfolio.getUserID());
        grid.add(welcome, 0, i);
        i++;

        //display the total amount of money in all cash accounts
        double totalMoney = 0;
        for (CashAccount c : myPortfolio.getCashAccounts()) {
            totalMoney += (c.getBalance());
        }
        Label total = new Label("Total Account Balance:  " + String.valueOf(totalMoney));
        grid.add(total, 0, i);
        i++;

        //LOGGER NAVIGATION START
        final Button logButton = new Button("Go to Logger");
        logButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.loggerScene(stage);
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
                main.transactionScene(stage);
            }
        });
        HBox transBox = new HBox();
        transBox.setAlignment(Pos.TOP_LEFT);
        grid.add(transactionButton, 0, i);
        //TRANSACTION NAVIGATION END

        //Create a button that leads to the simulation screen
        Button marketSimulation = new Button("MarketSimulation");
        grid.add(marketSimulation, 1, i);
        marketSimulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.simulationScene(window, port);
            }
        });
        i++;

        //Create a button that leads to the page to add an account
        Button addAccount = new Button("Add a Cash Account");
        addAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.addCashAccountScene(window, userID);
            }
        });

        grid.add(addAccount, 0, i);
        Button deleteAccount = new Button("Delete a Cash Account");
        deleteAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.deleteCashAccountScene(window);
            }
        });
        grid.add(deleteAccount, 1, i);

        window.setScene(portScene);
        window.show();
    }

}