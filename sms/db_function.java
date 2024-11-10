package sms;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_function {
    // This method returns true if registration is successful and false otherwise.
    public static boolean registerStudent(Connection conn, String firstName, String lastName, String username, String password) {
        // Hashes the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        // defines an INSERT query.
        // VALUES (?, ?, ?, ?) = placeholders for values. This prevents attackers from modifying the query itself since the values are treated as non-executable data.
        String sql = "INSERT INTO students (first_name, last_name, username, hash_password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the parameters for the SQL query
            /* setString methods replace each ? placeholder with actual values, in order. For instance:
               stmt.setString(1, firstName); binds firstName to the first ?. */
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, hashedPassword);

            // Execute the insert query
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registration successful.");
                return true;
            } else {
                System.out.println("Registration failed.");
                return false;
            }
          // Handles any SQL errors that occur.
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
            return false;
        }
    }

    public static boolean loginStudent(Connection conn, String username, String password) {
        // This SQL query selects the hash_password from the students table where the username matches the input.
        String sql = "SELECT hash_password FROM students WHERE username = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the username for the query
            stmt.setString(1, username);
            // ResultSet holds any rows returned by the query.
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Get the stored hashed password
                String storedHashedPassword = rs.getString("hash_password");
                // Compare the stored hash with the entered password
                if (BCrypt.checkpw(password, storedHashedPassword)) {
                    System.out.println("Login successful!");
                    return true;
                } else {
                    System.out.println("Invalid password.");
                    return false;
                }
            } else {
                System.out.println("Username not found.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            return false;
        }
    }
}
