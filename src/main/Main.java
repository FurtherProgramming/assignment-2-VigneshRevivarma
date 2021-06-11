package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
    //Main Method to run the program.
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Table Booking System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
