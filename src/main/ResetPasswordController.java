package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {
    public ResetPasswordModel resetPasswordModel = new ResetPasswordModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField username;
    @FXML
    private TextField answer;
    @FXML
    private Label secretquestion;
    @FXML
    private Label newPassword;
    @FXML
    Button resetPasswordButton;
    private String generatedString;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (resetPasswordModel.isDbConnected()){
            isConnected.setText("");
        }else{
            isConnected.setText("Not Connected");
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
    // Used to find the username in the database.
    public void Find(ActionEvent event) throws Exception  {
        String q = resetPasswordModel.getSecretQuestion(username.getText());
        secretquestion.setText(q);
    }
    // Random Generated Password for when the user answers the secret question right.
    public void Reset(ActionEvent event) throws Exception  {
        if (resetPasswordModel.getAnswer(username.getText()).contains(answer.getText())){
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 4;
            Random random = new Random();

            generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            newPassword.setText(generatedString);
        }
    }
    // User presses this after they receive the new password from the program and can return to the login menu.
    public void Ok(ActionEvent event) throws Exception  {
        if(resetPasswordModel.updateNewPassword(generatedString, username.getText())){
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
    //11.2.3 big sur
}