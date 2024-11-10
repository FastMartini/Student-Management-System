package sms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect_db{
    // jdbc:postgresql://: The protocol for PostgreSQL JDBC.
    // localhost: The server location (localhost means it's on the same computer).
    // 5432: The port number where PostgreSQL runs (default).
    // SMS_2.0: The database name.
    private static final String URL = "jdbc:postgresql://localhost:5432/SMS_2.0";
    // String USER = "your_db_username"
    private static final String USER = "postgres";
    // String PASSWORD = "your_db_password"
    private static final String PASSWORD = "";

    // Returns a connection to the database.
    public static Connection connect() {
        /* Initializes the conn variable to null.
           This will hold the database connection if it’s successful. */
        Connection conn = null;
        // Attempts to connect to the database.
        try {
            // DriverManager.getConnection(URL, USER, PASSWORD): initiates the connection.
            /* Requires:
            The database URL (URL).
            The username (USER).
            The password (PASSWORD).
            */
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection Successful.");
        // If connecting to the database fails, the SQLException exception is caught here.
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            // Outputs the exact error and stack trace for debugging purposes.
            e.printStackTrace();
        }
        return conn;
    }
    // entry point for program. When you run connect_db, the main method will execute first.
    public static void main(String[] args) {
        // Returns a connection to the database
        connect();
    }
}
