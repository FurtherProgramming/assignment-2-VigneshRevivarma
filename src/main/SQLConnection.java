package main;
import java.sql.*;


public class SQLConnection {

    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbpassword";
    private static final String SQCONN = "jdbc:sqlite:assignment.sqlite";

    public static Connection connect(){
        try{
          Class.forName("org.sqlite.JDBC");
          Connection connection = DriverManager.getConnection("jdbc:sqlite:assignment.db");
          return connection;

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
