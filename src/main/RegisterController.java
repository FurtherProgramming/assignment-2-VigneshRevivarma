package main;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    public RegisterModel registerModel = new RegisterModel();

    @FXML
    private TextField r_id;
    @FXML
    private TextField r_fname;
    @FXML
    private TextField r_lname;
    @FXML
    private TextField r_question;
    @FXML
    private TextField r_answer;
    @FXML
    private TextField r_username;
    @FXML
    private PasswordField r_password;
    @FXML
    private TextField role;
    @FXML
    Button registerButton;
    @FXML
    Button backButton;
    @FXML
    private Label isConnected;
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (registerModel.isDbConnected()) {
            this.isConnected.setText("");
        } else {
            this.isConnected.setText("Not Connected");
        }
    }
    //Data entered by user will go directly to the database.
    public void Register(Event event) throws Exception {
        if (registerModel.addEmployee(r_id.getText(), r_fname.getText(), r_lname.getText(), r_question.getText(), r_answer.getText(), r_username.getText(), r_password.getText(), role.getText()) == true) {
            isConnected.setText("Successful!!");
            try {
                Node node = (Node) event.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                Parent createAcc = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene sceneCreate = new Scene(createAcc);
                Stage secondaryStage = new Stage();
                secondaryStage.setScene(sceneCreate);
                secondaryStage.setTitle("Login");
                secondaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
                }
            }
        }

    //Return to the login main screen option.
    public void Back(ActionEvent event) throws Exception  {
        try {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            Parent createAcc = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene sceneCreate = new Scene(createAcc);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(sceneCreate);
            secondaryStage.setTitle("Table Booking System");
            secondaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

