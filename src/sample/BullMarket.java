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
 * @author Nicholas Baxter
 */

//equity increases in this simulation
public class BullMarket implements MarketSimulation {

    Portfolio tempPort;

    //Equity equityOriginator; //have to use a portfolio
  //  EquityCaretaker caretaker = new EquityCaretaker();

    /**
     * Class to run the simulation, implements Market simulation interface
     * @param percentage - percent the equity should change by
     * @param EQ - the portfolio being changed by the simulation
     * @param continuous - if the simulation is continuous or not
     * @param stepNum - the number of steps to go through
     * @param timeInterval - the time interval (day month year)
     * @return - the updated value of the equities
     */
    @Override
    public double runSimulation(float percentage, Portfolio EQ, boolean continuous, int stepNum, String timeInterval) {

//      equityOriginator.setEquityList(EQ);
//      caretaker.addMemento(equityOriginator.saveToMemento());
        float porfolioValue = 0;
        double equityValue;
        int steps = stepNum;
        tempPort = EQ;
            EQ.getTotalHoldings(); {
           // System.out.println(EQ.getTotalEquities() + " intial share price of equity");
            equityValue = EQ.getTotalEquities();
            double percentagePaid = EQ.getTotalEquities() * (percentage / 100);
          //  System.out.println(percentagePaid + " percent paid on equity");
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
        //System.out.println(porfolioValue + " final portfolio Value of ALL equity and shares");
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
