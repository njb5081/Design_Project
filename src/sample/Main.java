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


public class Main extends Application {

    Stage window;
    Scene scene1, scene2;
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
    public static void main(String[] args) {
        launch(args);
    }
}
