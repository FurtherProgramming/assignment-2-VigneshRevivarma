package main;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class ResetPasswordModel {

    Connection connection;

    public ResetPasswordModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }
    //Checking for database connection.
    public Boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }
    // Used to retrieve user's secret question.
    public String getSecretQuestion(String user) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select secretquestion from Employee where username = ?";
        String result = "";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);


            resultSet = preparedStatement.executeQuery();
            result = resultSet.getString(1);
            return result;
        }
        catch (Exception e)
        {
            return result;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    //Checking the answer of the secret question which is required to crosscheck if it is same as what the user entered.
    public String getAnswer(String user) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select answer from employee where username = ?";
        String result = "";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);


            resultSet = preparedStatement.executeQuery();
            result = resultSet.getString(1);
            return result;
        }
        catch (Exception e)
        {
            return result;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    // Updates the user's password using the random string and automatically updates in the database.
    public Boolean updateNewPassword(String newpassword, String username) throws SQLException {
        PreparedStatement preparedStatement = null;

        String query = "UPDATE Employee set password =? WHERE username =?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newpassword);
            preparedStatement.setString(2, username);
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