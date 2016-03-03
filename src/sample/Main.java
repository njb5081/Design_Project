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

    private String user = "mcd4874";
    private String userPassword = "1234";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Welcome");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 300);


        Text sceneTitle = new Text("welcome");
        sceneTitle.setFont(Font.font("Arial"));
        grid.add(sceneTitle, 0 , 0 ,2 ,1);

        //user input
        Label username = new Label("username");
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
        box.getChildren().add(login);
        grid.add(box, 1 , 4);

        //action for button
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(userField.getText().toString() == user && pwBox.getText().toString() == userPassword){
                    message.setFill(Color.FIREBRICK);
                    message.setText("successful sign in");
                } else {
                    System.out.println(userField.getText().toString());
                    System.out.println(pwBox.getText().toString());
                    message.setText("Please enter correct information");
                }
            }
        });

        //add register account
        Label noAccount = new Label("need new Account?");
        noAccount.autosize();
        grid.add(noAccount, 0, 5);
        Button register = new Button("Register");
        HBox box2 = new HBox(10);
        box2.setAlignment(Pos.BOTTOM_RIGHT);
        box2.getChildren().add(register);
        grid.add(box2, 1, 5);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
