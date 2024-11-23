package sms;
import java.util.Scanner;
import java.sql.Connection;
import static sms.db_function.isValidPassword;

public class main {

    public static void main(String[] args) {
        Connection conn = connect_db.connect(); // Get the database connection
        Scanner scanner = new Scanner(System.in);
        String password;

        // Register student
        System.out.println("Register a new student:");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();

        do {
            System.out.println("");
            System.out.println("Password must be at least 8 characters long and contain an uppercase letter, a lowercase letter, a number, and a special character.");
            System.out.print("Password: ");
            password = scanner.nextLine();

            // if password is correct, while loop will break
            if (isValidPassword((password))) {
                break;
            }
        } while (true);


        /* Calls the registerStudent method from the db_function class,
           passing conn, firstName, lastName, username, and password. */
        db_function.registerStudent(conn, firstName, lastName, username, password);

        // Login student
        System.out.println("\nLogin:");
        System.out.print("Username: ");
        username = scanner.nextLine();
        System.out.print("Password: ");
        password = scanner.nextLine();

        /* Retrieves the hashed password for the entered username,
           compares it to the input password, and authenticates if they match. */
        db_function.loginStudent(conn, username, password);

        scanner.close();
    }
}
