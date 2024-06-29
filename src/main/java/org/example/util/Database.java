package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private static final Logger logger = Logger.getLogger(Database.class.getName());

    public static Connection getConnection() {

        // JDBC URL for in-memory H2 database
        String jdbcUrl = "jdbc:h2:mem:testdb";
        Connection conn = null;
        try {
            // Register JDBC driver
            Class.forName("org.h2.Driver");
            // Open a connection to the database
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return conn;
    }
    public static void createSampleData(){
        Connection conn = getConnection();
        try{
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL)";
            conn.createStatement().executeUpdate(createTableSQL);

            // Insert values into the table
            String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, "John Doe");
            pstmt.setString(2, "john.doe@example.com");
            pstmt.executeUpdate();

            pstmt.setString(1, "Jane Smith");
            pstmt.setString(2, "jane.smith@example.com");
            pstmt.executeUpdate();

            System.out.println("Values inserted successfully.");

            // Close the connections
            pstmt.close();
            conn.close();
        }catch (SQLException e) {
           logger.log(Level.SEVERE, e.getMessage());
        }
    }
}

