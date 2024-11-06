package sms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect_db{
    private static final String URL = "jdbc:postgresql://localhost:5432/SMS_2.0";
    private static final String USER = "postgres"; // String USER = "your_db_username"
    private static final String PASSWORD = ""; // String PASSWORD = "your_db_password"

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection Successful.");
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        connect();
    }
}
