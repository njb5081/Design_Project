package sample.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.User;
import sample.handleData.data;
import sample.handleData.handleEquity;
/**
 * Created by minhduong on 4/4/16.
 */
public class accountHandler {
    Stage window;
    Scene scene1, scene2;
    String user;
    static data userData = new data();
    static portfolioHandler portfolioHandle = new portfolioHandler();
    private handleEquity handler;
    /**
     * Shows the first screen that allows a user to login to a portfolio
     * @param mainStage
     */
    public void loginScene(Stage mainStage){
        window = mainStage;
        window.setTitle("Login Page");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));

        scene1 = new Scene(grid, 500, 300);



        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Arial"));
        grid.add(sceneTitle, 0 , 0 ,2 ,1);

        //user input
        final Label username = new Label("Username");
        grid.add(username, 0, 1);
        final TextField userField = new TextField();
        grid.add(userField, 1, 1);
        final Label password = new Label("Password");
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
                user = userField.getText();
                //if the user account has been created, then the user can login
                if(userData.isUserExist(loginUser)){
                    message.setFill(Color.FIREBRICK);
                    message.setText("Successful Login");
                    portfolioHandle.portfolioScene(window, loginUser.username());
                } else {

                    message.setText("Please enter correct information");

                }
            }
        });
        box.getChildren().add(login);
        grid.add(box, 1 , 4);

        //add register account
        Label noAccount = new Label("Need an account?");
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

    /**
     * Shows the page that allows for registering new users/portfolios
     * @param mainStage
     */
    public void registerScene(Stage mainStage){
        //scene 2 register
        window = mainStage;
        window.setTitle("Register Page");
        //window.setScene(scene2);
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(15);
        grid2.setVgap(15);
        grid2.setPadding(new Insets(25, 25, 25, 25));

        scene2 = new Scene(grid2, 500, 300);

        Text registerSceneTitle = new Text("");
        registerSceneTitle.setFont(Font.font("Arial"));
        grid2.add(registerSceneTitle, 0 , 0 ,2 ,1);

        //user input
        Label username = new Label("Username");
        grid2.add(username, 0, 1);
        final TextField userField = new TextField();
        grid2.add(userField, 1, 1);
        final Label password = new Label("Password");
        grid2.add(password, 0, 2);
        final PasswordField pwBox = new PasswordField();
        grid2.add(pwBox, 1, 2);
        Label confirm = new Label("Confirm Password");
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
                //confirm the password for registration
                if (pwBox.getText().equals(confirmPw.getText())){
                    User newAccount = new User(userField.getText(),pwBox.getText());
                    //check whether someone has used this username or not
                    if(!userData.usernameExist(newAccount.username())) {
                        userData.saveAccount(newAccount);
                        message.setText("Successful Registration");
                    } else {
                        message.setText("Username is taken");
                    }
                } else {
                    message.setText("Please confirm the password");

                }
            }
        });
        box.getChildren().add(register);
        grid2.add(box, 1 , 4);

        //add register account
        Label needLogin = new Label("Have an account?");
        needLogin.autosize();
        grid2.add(needLogin, 0, 6);
        Button login = new Button("Login");
        //move to the login scene
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
    }


    public void timeIntervalUpdate(Stage mainStage, final String username){
        handler = new handleEquity();
        //scene 2 register
        window = mainStage;
        window.setTitle("update share price in the time interval");
        //window.setScene(scene2);
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(15);
        grid2.setVgap(15);
        grid2.setPadding(new Insets(25, 25, 25, 25));

        scene2 = new Scene(grid2, 500, 500);

        Text registerSceneTitle = new Text("");
        registerSceneTitle.setFont(Font.font("Arial"));
        grid2.add(registerSceneTitle, 0 , 0 ,2 ,1);

        //user input
        Label timer = new Label("SetTimer");
        grid2.add(timer, 0, 1);
        final TextField timeField= new TextField();
        grid2.add(timeField, 1, 1);

        //add button set timer
        Button setTimer = new Button("Set Timer");
        HBox box = new HBox(10);
        box.setAlignment(Pos.BOTTOM_RIGHT);

        final Text message = new Text();
//        action for button
        setTimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //confirm the password for registration
                int time = Integer.parseInt(timeField.getText());
                if(time < 8){
                    message.setText("Enter at least 8 seconds for the program to run");
                } else {
                    handler.updateSharePriceTimer(time);
                    portfolioHandle.portfolioScene(window,username);
                }
            }
        });

        //add button to go back to the profile page
        Button toPortfolio = new Button("Go to Portfolio");
        toPortfolio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                portfolioHandle.portfolioScene(window, username);
            }
        });

        box.getChildren().add(setTimer);
        box.getChildren().add(toPortfolio);
        grid2.add(box, 1 , 4);
        grid2.add(message,1,6);

        window.setScene(scene2);
        window.show();
    }
}