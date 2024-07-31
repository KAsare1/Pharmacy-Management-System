package com.example.pharmacy_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy_management_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

    private DBConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnect() throws SQLException {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection error", e);
            throw e;
        }
    }

    public static void closeConnection(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error closing resource", e);
            }
        }
    }
}
