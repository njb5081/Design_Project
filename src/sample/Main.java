package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;


public class Main extends Application {

    Stage window;
    Scene scene1, scene2, scene3;
    data userData = new data();

    @Override


    public void start(Stage primaryStage) throws Exception{
        loginScene(primaryStage);


    }

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
                    simulationScene(window);

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
                        message.setText("register sucess");
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

    public void simulationScene (Stage mainStage){
        window = mainStage;
        window.setTitle("Market simulation");


//        System.out.println("IN GUI CODE");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene3 = new Scene(grid, 300, 300);

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
    public static void main(String[] args) {
        launch(args);
    }
}
