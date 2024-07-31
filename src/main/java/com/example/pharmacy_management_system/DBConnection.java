package com.example.pharmacy_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy_management_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";


    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        try{
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
