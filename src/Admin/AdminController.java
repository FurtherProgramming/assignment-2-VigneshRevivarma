package Admin;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.SQLConnection;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField securityquestion;
    @FXML
    private TextField answer;

    @FXML
    private TableView<EmployeeData> employeetable;

    @FXML
    private TableColumn<EmployeeData, String> idcolumn;
    @FXML
    private TableColumn<EmployeeData, String> firstnamecolumn;
    @FXML
    private TableColumn<EmployeeData, String> lastnamecolumn;
    @FXML
    private TableColumn<EmployeeData, String> questioncolumn;
    @FXML
    private TableColumn<EmployeeData, String> answercolumn;

    private SQLConnection dc;
    private ObservableList<EmployeeData> data;

    private String sql = "SELECT * FROM Employee";


    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new SQLConnection();
    }
    // Used to Load Current/Updated Employee Data when opening the Dashboard.
    @FXML
    private void loadEmployeeData(ActionEvent event) throws SQLException {
        try {
            Connection conn = SQLConnection.connect();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                this.data.add(new EmployeeData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }

        this.idcolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("firstName"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("lastName"));
        this.questioncolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("securityQuestion"));
        this.answercolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("answer"));

        this.employeetable.setItems(null);
        this.employeetable.setItems(this.data);
    }
    // Used to Add new Employee Data without the username or password.
    @FXML
    private void addEmployee(ActionEvent event) {
        String sqlInsert = "INSERT INTO Employee(id,firstname,lastname,secretquestion,answer) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = SQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.firstname.getText());
            stmt.setString(3, this.lastname.getText());
            stmt.setString(4, this.securityquestion.getText());
            stmt.setString(5, this.answer.getText());

            stmt.execute();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Used to Erase the fields if admin makes a mistake in typing.
    @FXML
    private void clearFields(ActionEvent event) {
        this.id.setText("");
        this.firstname.setText("");
        this.lastname.setText("");
        this.securityquestion.setText("");
        this.answer.setText("");
    }
    // Used to Delete an Employee (Read README file on how to use this).
    @FXML
    private void deleteEmployee(ActionEvent event) {

        String sql = "DELETE FROM Employee where ID = ?";
        try {
            Connection conn = SQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id.getText());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Used to Print CSV file of all Employees.
    @FXML
    private void csvEmployee(ActionEvent event) {
        Connection conn = SQLConnection.connect();
            try {
                PrintWriter pw = new PrintWriter("EmployeeReport.csv");
                StringBuilder sb = new StringBuilder();
                ResultSet rs=null;

                String query = "SELECT ID, firstname, lastname, secretquestion, answer from Employee";
                PreparedStatement ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                while (rs.next()) {
                    sb.append(rs.getString("ID"));
                    sb.append(",");
                    sb.append(rs.getString("First Name"));
                    sb.append(",");
                    sb.append(rs.getString("Last Name"));
                    sb.append(",");
                    sb.append(rs.getString("Secret Question"));
                    sb.append(",");
                    sb.append(rs.getString("Answer"));
                    sb.append("\r\n");
                }

                pw.write(sb.toString());
                pw.close();

            } catch (FileNotFoundException | SQLException e) {
                e.printStackTrace();
            }
    }
}
