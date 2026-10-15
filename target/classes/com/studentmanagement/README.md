
# Student Database Management System

A Java application that connects to MySQL using JDBC and Maven to manage student records.

## Technologies Used

- Java 21
- JDBC
- MySQL
- Maven
- MySQL Connector/J

## Features

- Connect Java to a MySQL database
- Insert student records
- Retrieve and display student records
- Update student marks
- Delete student records
- Use PreparedStatement for parameterized SQL queries
- Demonstrate transaction commit and rollback

## Database Setup

1. Install and start MySQL Server.
2. Create the database and required tables.
3. Configure the database name, username, and password in DatabaseConnection.java.
4. Ensure the students table and departments foreign-key relationship match the SQL statements used by the application.

## Run the Project

Compile the project:

```bash
mvn clean compile
```

Run the CRUD demonstration:

```bash
mvn org.codehaus.mojo:exec-maven-plugin:3.5.1:java -Dexec.mainClass=com.studentmanagement.StudentDAO
```

Run the rollback demonstration:

```bash
mvn org.codehaus.mojo:exec-maven-plugin:3.5.1:java -Dexec.mainClass=com.studentmanagement.TransactionDemo
```

Run the commit demonstration:

```bash
mvn org.codehaus.mojo:exec-maven-plugin:3.5.1:java -Dexec.mainClass=com.studentmanagement.TransactionCommitDemo
```

## Security Note

Do not commit real database passwords or credentials to GitHub.
