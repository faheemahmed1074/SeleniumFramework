package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtils {
    private Connection connection;

    // Establishes a database connection
    public void connect(String dbUrl, String username, String password) throws Exception {
        String connectionUrl = "jdbc:sqlserver://" + dbUrl;
        connection = DriverManager.getConnection(connectionUrl, username, password);
        System.out.println("Connected to the database.");
    }

    // Executes a SQL query and returns a ResultSet
    public ResultSet executeQuery(String query) throws Exception {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    // Closes the database connection
    public void closeConnection() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed.");
        }
    }
}
