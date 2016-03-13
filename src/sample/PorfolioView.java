package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Nick on 3/12/2016.
 */

public class PorfolioView extends Application {

    Stage window;
    Scene scene1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("My Portfolio");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        scene1 = new Scene(grid, 300, 300);
    }
    public void portfolioScene(Stage stage, String userid){

    }

    public static void main(String[] args) {

        launch(args);

    }
}
