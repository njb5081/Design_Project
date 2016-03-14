package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class BearMarket implements MarketSimulation {
    Portfolio tempPort;
    @Override
    public double runSimulation(float percentage, Portfolio EQ, boolean continuous, int stepNum, String timeInterval) {
        //originator.setEquityList(EQ);
        //  caretaker.addMemento(originator.saveToMemento());
        float porfolioValue = 0;
        double equityValue;
        int steps = stepNum;
        Portfolio tempPort;
        //TODO MAKE the simulation able to step through

            System.out.println(EQ.getTotalEquities() + " intial share price of equity");
            equityValue = EQ.getTotalEquities();
            double percentagePaid = (EQ.getTotalEquities()) * (percentage / 100);

            System.out.println(percentagePaid + " percent paid on equity");
            if (timeInterval.equals("m")) {
                percentagePaid = percentagePaid * 30;
            } else if (timeInterval.equals("y")) {
                percentagePaid = percentagePaid * 365;
            }
            //System.out.println(percentagePaid + " Percent paid");
            while (steps > 0) {
                //  System.out.println(stepNum + " step num");
                // System.out.println(E.equityPrice + " Price");
                equityValue -= (percentagePaid);
                if (equityValue < 0){
                    equityValue = 0;
                }
                steps -= 1;





            porfolioValue += equityValue;


            steps = stepNum;
            //equityValue += E.getSharePrice();
        }
        System.out.println(porfolioValue + " final portfolio Value of ALL equity and shares");
        return EQ.getTotalEquities();
    }

//    public Portfolio savePortfolio(Portfolio port){
//        tempPort = new Portfolio(port.getUserID(),port.getEquities(),port.getCashAccounts());
//        return tempPort;
//    }

    @Override
    public double reset() {

        //  originator.RestoreFromEquityMemento(caretaker.getMemento());
        // EQ = originator.getState();


        return tempPort.getTotalHoldings();
    }
}




