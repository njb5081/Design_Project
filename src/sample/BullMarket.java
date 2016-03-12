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
    public BullMarket bull;
    //Equity equityOriginator; //have to use a portfolio
    EquityCaretaker caretaker = new EquityCaretaker();

    @Override
    public ArrayList<Equity> runSimulation(float percentage, ArrayList<Equity> EQ, boolean continuous, int stepNum, String timeInterval) {

//        equityOriginator.setEquityList(EQ);
     //   caretaker.addMemento(equityOriginator.saveToMemento());
        float porfolioValue = 0;
        double equityValue = 0;
        int steps = stepNum;
        //TODO MAKE the simulation able to step through
        for (Equity E : EQ) {
            System.out.println(E.getSharePrice() + " intial share price of equity");
            equityValue = E.getSharePrice();
            double percentagePaid = E.getSharePrice() * (percentage / 100);
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
                equityValue += (percentagePaid);
                steps -= 1;

            }
            E.setSharePrice(equityValue);
            equityValue *= E.getSharesHeld();
            equityValue = Math.floor(equityValue);
            porfolioValue += equityValue;

            System.out.println(equityValue + " final equity value of ALL shares of " + E.getName());
            steps = stepNum;
            //equityValue += E.getSharePrice();
            for (Equity lll : EQ){
                System.out.println(lll.getSharePrice() + " price after sim");
            }
        }
        System.out.println(porfolioValue + " final portfolio Value of ALL equity and shares");
        return EQ;
    }

    @Override
    public ArrayList<Equity> reset (ArrayList<Equity> EQ) {

      //  originator.RestoreFromEquityMemento(caretaker.getMemento());
       // EQ = originator.getState();
        for (Equity EEE : EQ) {
            System.out.println(EEE.getSharePrice() + " attempted reset");
        }

        return EQ;
    }

}
