package main;


import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class RegisterModel {

    Connection connection;

    public RegisterModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }
    // Checking database connection.
    public Boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }
    // Stores the user's input in the form and stores it as their respective values in the database.
    public Boolean addEmployee(String r_id, String r_fname, String r_lname, String r_question, String r_answer, String r_username, String r_password, String role) throws SQLException {
        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO Employee(id, firstname,lastname, secretquestion, answer, username, password, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, r_id);
            preparedStatement.setString(2, r_fname);
            preparedStatement.setString(3, r_lname);
            preparedStatement.setString(4, r_question);
            preparedStatement.setString(5, r_answer);
            preparedStatement.setString(6, r_username);
            preparedStatement.setString(7, r_password);
            preparedStatement.setString(8, role);
            preparedStatement.execute();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }finally {
            preparedStatement.close();
        }
    }
}