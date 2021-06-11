package main;

import Admin.AdminController;
import Employee.EmployeeController;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label loginStatus;


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (this.loginModel.isDbConnected()){
            this.isConnected.setText("Connected");
        }else{
            this.isConnected.setText("Not Connected");
        }

        this.combobox.setItems(FXCollections.observableArrayList(option.values()));

    }
    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event){

        try {
            if (this.loginModel.isLogin(this.username.getText(),this.password.getText(),((option)this.combobox.getValue()).toString())){
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                switch(((option)this.combobox.getValue()).toString()) {
                    case "Admin":
                        adminLogin();
                        break;
                    case "Employee":
                        employeeLogin();
                        break;
                }
            }else{
                this.loginStatus.setText("Wrong Credentials");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Login Dashboard used for an Employee.
    public void employeeLogin() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/Employee/employeeFXML.fxml").openStream());

            EmployeeController employeeController = (EmployeeController) loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Employee Dashboard");
            userStage.setResizable(false);
            userStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // Login Dashboard used for an Admin.
    public void adminLogin() {
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane) adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());

            AdminController adminController = (AdminController) adminLoader.getController();

            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // Register form
    public void Register(ActionEvent event) throws Exception{

        try {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            Parent createAcc = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Scene sceneCreate = new Scene(createAcc);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(sceneCreate);
            secondaryStage.setTitle("Register Form");
            secondaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Reset Password form
    public void resetPassword(ActionEvent event) throws Exception  {
        try {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            Parent createAcc = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
            Scene sceneCreate = new Scene(createAcc);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(sceneCreate);
            secondaryStage.setTitle("Reset Password");
            secondaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    //11.2.3 big sur
    }
}
