package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/7/2016.
 */

//equity increases
public class BullMarket implements MarketSimulation {

    Portfolio tempPort;

    //Equity equityOriginator; //have to use a portfolio
  //  EquityCaretaker caretaker = new EquityCaretaker();

    @Override
    public double runSimulation(float percentage, Portfolio EQ, boolean continuous, int stepNum, String timeInterval) {

//      equityOriginator.setEquityList(EQ);
//      caretaker.addMemento(equityOriginator.saveToMemento());
        float porfolioValue = 0;
        double equityValue;
        int steps = stepNum;
        tempPort = EQ;
        //TODO MAKE the simulation able to step through
            EQ.getTotalHoldings(); {
            System.out.println(EQ.getTotalEquities() + " intial share price of equity");
            equityValue = EQ.getTotalEquities();
            double percentagePaid = EQ.getTotalEquities() * (percentage / 100);
            System.out.println(percentagePaid + " percent paid on equity");
            if (timeInterval.equals("m")) {
                percentagePaid = percentagePaid * 30;
            } else if (timeInterval.equals("y")) {
                percentagePaid = percentagePaid * 365;
            }
            while (steps > 0) {
                equityValue += (percentagePaid);
                steps -= 1;
            }

            //equityValue *= E.getSharesHeld();
            equityValue = Math.floor(equityValue);
            porfolioValue += equityValue;

            System.out.println(equityValue + " final equity value of ALL shares of ");
            steps = stepNum;

        }
        System.out.println(porfolioValue + " final portfolio Value of ALL equity and shares");
        return EQ.getTotalEquities();
    }

//    public Portfolio savePortfolio(Portfolio port){
//
//        tempPort = new Portfolio(port.getUserID(),port.getTotalEquities(),port.getCashAccounts());
//        return tempPort;
//    }

    @Override
    public double reset() {

      //  originator.RestoreFromEquityMemento(caretaker.getMemento());
       // EQ = originator.getState();

        return tempPort.getTotalHoldings();
    }

}
