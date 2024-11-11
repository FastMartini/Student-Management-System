# Student-Management-System

# Inspiration
Having recently learned PostgreSQL, I wanted to apply my skills in a real-world project, building on my Java experience to deepen my knowledge of database management. This project combines my existing Java skills with SQL, allowing me to explore the integration of backend and database technologies.

# What it does
This project implements a secure login system in Java with PostgreSQL as the database. Users input their login credentials via the terminal, where Java processes and validates the data before saving it to a PostgreSQL database table. The focus is on secure storage and efficient data handling, demonstrating integration between Java and PostgreSQL for effective user authentication, data storage, and retrieval. This project is ideal for applications needing a reliable and scalable user credential management system.

# How I built it
To facilitate user registration and authentication, I designed and implemented a PostgreSQL database, alongside a dedicated table to store the necessary data for each student’s account. The table includes several essential fields: student_id (which serves as the primary key to uniquely identify each record), first_name, last_name, username, and hashed_password (for secure password storage, which will be detailed later).

To enable seamless communication between the Java application and the PostgreSQL database, I downloaded the latest JDBC driver from PostgreSQL. This driver allowed me to leverage the DriverManager.getConnection() method from the java.sql.DriverManager package, bridging Java and PostgreSQL. However, to properly configure the connection, it was necessary to specify several constants, including the **URL** (the database location), **USER** (the database username), and **PASSWORD** (the associated password). These parameters are easily accessible via pgAdmin4 under the _Properties_ section. The connection is established through the following statement: **DriverManager.getConnection(URL, USER, PASSWORD);**. For further insight into how the connection is configured and implemented, refer to the code in the connect_db class.

Given the increasing prevalence of cyberattacks, it is crucial to ensure that sensitive data, such as user passwords, remains secure. With this in mind, I prioritized the implementation of secure password storage in my database. To achieve this, I utilized BCrypt from mindrot.org to securely hash passwords before they are stored. This method applies a computationally expensive hashing algorithm, making it significantly harder for attackers to reverse-engineer the original passwords, even if the database is compromised. By hashing passwords in this way, I ensure that user credentials remain protected against potential breaches. Additionally, to prevent SQL injection attacks, I used PreparedStatements for all database queries. By using parameterized queries, PreparedStatements ensure that user input is treated as data, not executable code, which safeguards the database from malicious manipulation. For further details on how BCrypt and prepare statements are implemented in the system, refer to the db_function class.

The registerStudent method processes user input—first name, last name, username, and password—by first hashing the password using BCrypt and then inserting the data into the students table through a parameterized SQL query. This approach ensures secure handling of user credentials. The loginStudent method, on the other hand, retrieves the stored hashed password associated with a given username from the database. It then compares the entered password (after hashing it) with the stored hash using BCrypt.checkpw(). If the password matches, the user is successfully authenticated; otherwise, an error message is displayed. The program will interact with users via the terminal, prompting them to enter their first name, last name, username, and password for registration, and later asking for the username and password to log in. Input is captured using the Scanner class, and the data is passed to the db_function class for processing. For a detailed look at the implementation, refer to the corresponding methods in the db_function class and the main class.

To ensure the robustness and reliability of the application, I implemented try-catch blocks throughout the program to handle potential SQLExceptions and connection errors. This mechanism prevents the application from crashing unexpectedly and provides informative error messages, which are essential for debugging and troubleshooting. Additionally, I thoroughly tested the functionality by adding multiple test users to the database and verifying that both the registration and login processes worked as intended. I made sure that only valid credentials allowed successful login, while incorrect credentials or missing user entries would trigger appropriate error messages. For further details on the error-handling implementation, refer to the relevant sections in the db_function and connect_db classes.

# Challenges I ran into
One of the first challenges I faced was securely storing passwords. Initially, I had considered storing passwords directly in the database, but quickly realized the security risks that would pose. To address this, I implemented BCrypt. While BCrypt provided a secure solution, I ran into difficulties with its integration.

The first hurdle was understanding how BCrypt works. Unlike traditional hashing algorithms, BCrypt adds a salt to each password, making it more secure against rainbow table attacks. I had to learn how to properly generate salts and hash passwords using the BCrypt.hashpw() method. The hashing process was not as straightforward as I initially expected, as I had to ensure the password hashing occurred before storing it in the database. Additionally, I needed to ensure that the login process correctly compared the entered password to the hashed value in the database using BCrypt.checkpw().

Connecting the Java application to PostgreSQL via JDBC was another challenge I faced. First, I had to download the correct JDBC driver for PostgreSQL from the official website, which I had not done prior to starting the project. This caused a delay at the beginning of the process, as I had to ensure that the driver was compatible with the version of PostgreSQL I was using. Once I had the JDBC driver in place, I faced issues in properly specifying the connection URL, username, and password. The connection string needed to be formatted precisely, and any mistakes would prevent the application from connecting to the database. For instance, I initially overlooked the fact that the database URL format needed to be specific to my local setup. Additionally, I had to ensure that the username and password I was using matched the credentials stored in pgAdmin4’s properties section. This process involved testing the connection in isolation before integrating it into the rest of the application, which added extra time for debugging and verification.

One of the more abstract challenges was managing multiple classes and ensuring seamless interaction between them. The project involved a separate class for handling database connections (connect_db), another for database operations (db_function), and a third for running the application (main). I initially struggled with how to pass data between classes, particularly how the user input captured in the main class would be passed to the db_function class for processing. The interaction between classes was essential for keeping the application organized, but there were moments when I had trouble ensuring that data was being passed correctly. For instance, I had to ensure that the connection object (Connection conn) was passed correctly from the connect_db class to the db_function class without losing its context.


# Accomplishments that I am proud of
One of the key accomplishments I am particularly proud of is the successful implementation of secure password storage using BCrypt. Additionally, I utilized prepared statements to insert and retrieve data securely from the database, further preventing SQL injection attacks. This accomplishment not only secured the application but also reinforced my understanding of cryptographic techniques and secure coding practices.

Another accomplishment I take pride in is the integration of Java with PostgreSQL via JDBC. Despite initial challenges in setting up the connection and ensuring the correct configuration of the connection string, I was able to establish a reliable link between the Java application and the database.


# What I learned
*  Learned how to use BCrypt for securely hashing passwords and protecting user credentials.
*  Gained experience in connecting a Java application to a PostgreSQL database using JDBC.
*  Understood the importance of using prepared statements to prevent SQL injection vulnerabilities.
*  Implemented try-catch blocks to handle SQL exceptions and prevent the application from crashing unexpectedly.

# Links
JDBC Driver for PostgreSQL:
[PostgreSQL JDBC Driver](https://jdbc.postgresql.org/)
This is the official JDBC driver for PostgreSQL, enabling integration between Java and PostgreSQL databases.

BCrypt:
[BCrypt - mindrot.org](https://www.mindrot.org/projects/jBCrypt/#download)
This page provides information about BCrypt, a secure password hashing algorithm used to protect passwords in the application.
