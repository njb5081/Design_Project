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
public class BullMarket extends Application implements MarketSimulation {
    public BullMarket bull;
    //EquityOriginator originator = new EquityOriginator();
    EquityCaretaker caretaker = new EquityCaretaker();

    @Override
    public float runSimulation(float percentage, ArrayList<equity> EQ, boolean continuous, int stepNum, String timeInterval) {

        //originator.setEquityList(EQ);
      //  caretaker.addMemento(originator.saveToMemento());
        float portfolioValue = 0;
        int steps = stepNum;
        //TODO MAKE the simulation able to step through
        for (equity E : EQ) {
            float percentagePaid = E.equityPrice * (percentage / 100);
            if (timeInterval.equals("month")) {
                percentagePaid = percentagePaid * 30;
            } else if (timeInterval.equals("year")) {
                percentagePaid = percentagePaid * 365;
            }
            //System.out.println(percentagePaid + " Percent paid");
            while (steps > 0) {
              //  System.out.println(stepNum + " step num");
               // System.out.println(E.equityPrice + " Price");
                E.equityPrice += (percentagePaid);
                steps -= 1;
            }
            steps = stepNum;
            portfolioValue += E.equityPrice;
        }
        return portfolioValue;
    }

    @Override
    public ArrayList<equity> reset (ArrayList<equity> EQ) {

      //  originator.RestoreFromEquityMemento(caretaker.getMemento());
       // EQ = originator.getState();
        for (equity EEE : EQ) {
            System.out.println(EEE.equityPrice + " attempted reset");
        }

        return EQ;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bull Market simulation");
//        System.out.println("IN GUI CODE");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 300, 300);

        final Label interval = new Label("Time interval: (day,month, year)");
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

        Button reset = new Button("Reset");
        HBox box4 = new HBox(10);
        box4.setAlignment(Pos.BOTTOM_RIGHT);
        box4.getChildren().add(reset);
        grid.add(box4, 1, 100);

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
                ArrayList<equity> equities = new ArrayList<equity>();
                equity eq = new equity();
                eq.equityPrice = 30;
                equity eq2 = new equity();
                eq2.equityPrice = 10;
                equities.add(eq);
                equities.add(eq2);
//                for (equity EEE : equities) {
//                    System.out.println(EEE.equityPrice + " before simulation");
//                }
                ArrayList<equity> tempList = new ArrayList<equity>();
                System.out.println(bull.runSimulation(tempPercent, equities, true, tempSteps, tempInterval) + " After simulation");
                //todo RESET FUNCTION NOT WORKING CORRECTLY
                tempList = bull.reset(equities);
                //todo Calling reset on wrong thing should be portfolio???
//                for (equity EEE : tempList) {
//                    System.out.println(EEE.equityPrice + " After reset");
//                }

            }

        });

        //todo THIS IS BREAKING
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
          //      bull.reset();
            }

            });
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);


    }
}
