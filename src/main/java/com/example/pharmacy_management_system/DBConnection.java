package com.example.pharmacy_management_system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://avnadmin:AVNS_1BVhXhdCxfXGYXIsdHZ@mysql-37c79811-asareamankwah2-a951.i.aivencloud.com:24833/DSA?ssl-mode=REQUIRED";
    private static final String DB_USER = "";
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
